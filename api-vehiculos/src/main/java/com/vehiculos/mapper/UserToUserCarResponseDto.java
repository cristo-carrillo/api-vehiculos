package com.vehiculos.mapper;

import com.vehiculos.dto.CarDto;
import com.vehiculos.dto.UserCarResponseDto;
import com.vehiculos.models.Car;
import com.vehiculos.models.User;

import java.util.List;

public class UserToUserCarResponseDto {

    private static List<CarDto> carUserToCarDto(List<Car> carList){
        return carList.stream().map(car->
            new CarDto(car.getId(), car.getBrand(), car.getModel(), car.getColor(), car.getModelYear(),
                    car.getVin(), car.getPrice(), car.getAvailability())
        ).toList();

    }
    public  static UserCarResponseDto userCarToUserDto(User user){
        return new UserCarResponseDto(user.getId(), user.getFirstName(),user.getLastName(), user.getAddress(),
                user.getEmail(), user.getPassword(), user.getBirthday(),carUserToCarDto(user.getCars()));
    }
}
