package com.vehiculos.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vehiculos.dto.CarRequestDto;
import com.vehiculos.models.Car;

import java.util.List;

public interface CarService {

    Car getCar(Long id);
    void createCar(CarRequestDto id) throws JsonProcessingException;
    List<Car> allCar();
    void updateCar(Long id, Car car);
}
