package com.vehiculos.controllers;

import com.vehiculos.exception.AlreadyExistsException;
import com.vehiculos.models.Car;
import com.vehiculos.services.CarServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
public class CarController {
    @Autowired
    private final CarServiceImp carServiceImp;
    @PostMapping("")
    public ResponseEntity saveUser(@RequestBody Car car) {

        Map<String, String> response = new HashMap<>();
        try{
            carServiceImp.createCar(car.getId());
            response.put("status", "201");
            response.put("message", "Vehiculo creado con éxito");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (AlreadyExistsException e){
            response.put("status", "409");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }catch (RuntimeException e){
            response.put("status", "400");
            response.put("message", "Error en los datos enviados");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
