package com.vehiculos.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vehiculos.exception.AlreadyExistsException;
import com.vehiculos.models.Car;
import com.vehiculos.repository.CarRepository;
import com.vehiculos.resttemplate.CarRestTemplate;
import com.vehiculos.resttemplate.CarRestTemplateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImp implements CarService{
    private final CarRestTemplate carRestTemplate;
    private final CarRepository carRepository;
    @Override
    public Car getCar(Long id) {
        return null;
    }

    @Override
    public void createCar(Long id){
        Optional<Car> car = carRepository.findById(id);
        if(car.isPresent()){
            throw new AlreadyExistsException(String.format("El vehículo con id %d ya existe ", id ));
        }
        try {
            CarRestTemplateModel carRestTemplateModel = carRestTemplate.getCarApiPublic(id);
            Car newCar = new Car(carRestTemplateModel.getId(), carRestTemplateModel.getCar(), carRestTemplateModel.getCar_model(),
                    carRestTemplateModel.getCar_color(), carRestTemplateModel.getCar_model_year(), carRestTemplateModel.getCar_vin(),
                    Double.parseDouble(carRestTemplateModel.getPrice().substring(1)), carRestTemplateModel.isAvailability());

            carRepository.save(newCar);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> allCar() {
        return null;
    }

    @Override
    public Boolean updateCar(Long id, Car car) {
        return null;
    }
}
