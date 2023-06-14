package com.vehiculos.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vehiculos.dto.CarRequestDto;
import com.vehiculos.dto.CarUserResponseDto;
import com.vehiculos.exception.AlreadyExistsException;
import com.vehiculos.exception.CarNotFoundException;
import com.vehiculos.exception.CarUpdateException;
import com.vehiculos.exception.UserNotFoundException;
import com.vehiculos.mapper.CarToCarUserResponseDto;
import com.vehiculos.models.Car;
import com.vehiculos.models.User;
import com.vehiculos.repository.CarRepository;
import com.vehiculos.repository.UserRepository;
import com.vehiculos.resttemplate.CarConsumerApiModel;
import com.vehiculos.resttemplate.CarRestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.vehiculos.utils.Constants.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CarServiceImp implements CarService {
    private final CarRestTemplate carRestTemplate;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Override
    public CarUserResponseDto getCar(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isEmpty()) {
            throw new CarNotFoundException(String.format("El carro con id %d No se encuentra registrado", id));
        }
        return CarToCarUserResponseDto.carUserToCarDto(car.get());
    }

    @Override
    public void createCar(CarRequestDto carRequestDto) {
        Optional<Car> car = carRepository.findById(carRequestDto.getId());
        if (car.isPresent()) {
            throw new AlreadyExistsException(String.format("El vehículo con id %d ya existe ", carRequestDto.getId()));
        }
        Optional<User> user = userRepository.findByEmail(carRequestDto.getEmailUser());
        if(user.isEmpty()){
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        try {
            CarConsumerApiModel carConsumerApiModel = carRestTemplate.getCarApiPublic(carRequestDto.getId());
            Car newCar = new Car(carConsumerApiModel.getId(), carConsumerApiModel.getCar(), carConsumerApiModel.getCarModel(),
                    carConsumerApiModel.getCarColor(), carConsumerApiModel.getCarModelYear(), carConsumerApiModel.getCarVin(),
                    Double.parseDouble(carConsumerApiModel.getPrice().substring(1)), carConsumerApiModel.isAvailability(),
                    user.orElseThrow());

            carRepository.save(newCar);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CarUserResponseDto> allCar() {

        return carRepository.findAll()
                .stream()
                .map(CarToCarUserResponseDto::carUserToCarDto)
                .toList();
    }

    @Override
    public void updateCar(Long id, Car car) {

        Optional<Car> carDBOptional = carRepository.findById(id);
        if (carDBOptional.isEmpty()) {
            throw new CarNotFoundException(String.format("El carro con id %d No se encuentra registrado", id));
        }
        Car carDB = carDBOptional.get();
        carDB.setBrand(car.getBrand());
        carDB.setModel(car.getModel());
        carDB.setColor(carDB.getColor());
        carDB.setModelYear(car.getModelYear());
        carDB.setVin(car.getVin());
        carDB.setPrice(car.getPrice());
        carDB.setAvailability(car.getAvailability());
        if(carRepository.save(carDB) == null){
            throw new CarUpdateException("Error al actualizar el vehiculo con id "+id);
        }

    }






}
