package com.vehiculos.controllers;

import com.vehiculos.dto.CarRequestDto;
import com.vehiculos.exception.AlreadyExistsException;
import com.vehiculos.exception.CarNotFoundException;
import com.vehiculos.exception.TokenIsEmptyException;
import com.vehiculos.exception.TokenIsNotValidException;
import com.vehiculos.models.Car;
import com.vehiculos.services.CarServiceImp;
import com.vehiculos.utils.ApiResponse;
import com.vehiculos.utils.ValidateToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.vehiculos.utils.Constants.*;

@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
public class CarController {
    @Autowired
    private final CarServiceImp carServiceImp;

    @Autowired
    private final ValidateToken validateToken;
    private ApiResponse apiResponse;


    @PostMapping("")
    public ResponseEntity<ApiResponse> saveCar(HttpServletRequest request, @RequestBody CarRequestDto car) {
        try {
            validateToken.isValid(request);
            carServiceImp.createCar(car);
            apiResponse = new ApiResponse(REGISTER_CREATED, "");
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (TokenIsEmptyException e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
        } catch (TokenIsNotValidException e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
        } catch (AlreadyExistsException e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_IMPLEMENTED);
        }

    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCar(HttpServletRequest request) {
        try {
            validateToken.isValid(request);
            apiResponse = new ApiResponse(REGISTER_LIST, carServiceImp.allCar());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (TokenIsEmptyException e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
        } catch (TokenIsNotValidException e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findCarById(HttpServletRequest request, @PathVariable Long id) {
        try {
            validateToken.isValid(request);
            apiResponse = new ApiResponse(REGISTER_FOUND, carServiceImp.getCar(id));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (TokenIsEmptyException e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
        } catch (TokenIsNotValidException e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
        } catch (CarNotFoundException e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCar(HttpServletRequest request, @PathVariable Long id, @RequestBody Car car) {
        try {
            validateToken.isValid(request);
            carServiceImp.updateCar(id, car);
            apiResponse = new ApiResponse(REGISTER_UPDATED, "");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (TokenIsEmptyException e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
        } catch (TokenIsNotValidException e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
        } catch (CarNotFoundException e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        } catch (AlreadyExistsException e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.PAYMENT_REQUIRED);
        } catch (Exception e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_IMPLEMENTED);
        }

    }
}
