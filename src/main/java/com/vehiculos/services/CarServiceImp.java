package com.vehiculos.services;

import com.vehiculos.models.Car;
import com.vehiculos.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CarServiceImp implements CarService{
    private final CarRepository carRepository;
    @Override
    public Car getCar(Long id) {
        return null;
    }

    @Override
    public Boolean createCar(Car car) {
        return null;
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
