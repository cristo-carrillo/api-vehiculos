package com.vehiculos.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vehiculos.dto.CarRequestDto;
import com.vehiculos.dto.CarUserResponseDto;
import com.vehiculos.models.Car;

import java.util.List;

public interface CarService {

    CarUserResponseDto getCar(Long id);
    void createCar(CarRequestDto id) throws JsonProcessingException;
    List<CarUserResponseDto> allCar();
    void updateCar(Long id, Car car);
}
