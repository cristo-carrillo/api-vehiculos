package com.vehiculos.mapper;

import com.vehiculos.dto.CarUserResponseDto;
import com.vehiculos.dto.UserDto;
import com.vehiculos.models.Car;
import com.vehiculos.models.User;

public class CarToCarUserResponseDto {

    public static CarUserResponseDto carUserToCarDto(Car carFind) {
        return new CarUserResponseDto(carFind.getId(), carFind.getBrand(), carFind.getModel(), carFind.getColor(),
                carFind.getModelYear(), carFind.getVin(), carFind.getPrice(), carFind.getAvailability(), userCarToUserDto(carFind.getUser()));
    }
    private static UserDto userCarToUserDto(User user){
        return new UserDto(user.getId(),
                user.getFirstName(), user.getLastName(), user.getAddress(), user.getEmail());

    }
}
