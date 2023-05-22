package com.vehiculos.controllers;

import com.vehiculos.dto.CarRequestDto;
import com.vehiculos.dto.CarUserResponseDto;
import com.vehiculos.exception.AlreadyExistsException;
import com.vehiculos.exception.CarNotFoundException;
import com.vehiculos.models.Car;
import com.vehiculos.services.CarServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vehiculos.utils.Constants.MESSAGE;
import static com.vehiculos.utils.Constants.STATUS;

@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
public class CarController {
    @Autowired
    private final CarServiceImp carServiceImp;

    @PostMapping("")
    public ResponseEntity<Map<String,String>> saveCar(@RequestBody CarRequestDto car) {

        Map<String, String> response = new HashMap<>();
        try {
            carServiceImp.createCar(car);
            response.put(STATUS, "201");
            response.put(MESSAGE, "Vehiculo creado con éxito");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            response.put(STATUS, "409");
            response.put(MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            response.put(STATUS, "400");
            response.put(MESSAGE, "Error en los datos enviados");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.put(STATUS, "500");
            response.put(MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @GetMapping
    public ResponseEntity<List<CarUserResponseDto>> getAllCar() {
        return ResponseEntity.ok(carServiceImp.allCar());
    }

    @GetMapping("/{id}")
    public ResponseEntity findCarById(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            return ResponseEntity.ok(carServiceImp.getCar(id));

        } catch (CarNotFoundException e) {
            response.put(STATUS, "404");
            response.put(MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put(STATUS, "500");
            response.put(MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCar(@PathVariable Long id, @RequestBody Car car) {
        Map<String, String> response = new HashMap<>();
        try {
            carServiceImp.updateCar(id, car);
            response.put(STATUS, "200");
            response.put(MESSAGE, "Vehiculo actualizado con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CarNotFoundException e) {
            response.put(STATUS, "404");
            response.put(MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (AlreadyExistsException e) {
            response.put(STATUS, "400");
            response.put(MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.PAYMENT_REQUIRED);
        } catch (Exception e) {
            response.put(STATUS, "500");
            response.put(MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
        }

    }
}
