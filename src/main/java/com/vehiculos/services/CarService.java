package com.vehiculos.services;

import com.vehiculos.models.Car;

import java.util.List;

public interface CarService {

    Car getCar(Long id);
    Boolean createCar(Car car);
    List<Car> allCar();
    Boolean updateCar(Long id, Car car);
}
