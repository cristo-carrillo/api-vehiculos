package com.vehiculos.controllers;

import com.vehiculos.dto.CarRequestDto;
import com.vehiculos.models.Car;
import com.vehiculos.services.CarServiceImp;
import com.vehiculos.utils.ApiResponse;
import com.vehiculos.utils.ValidateToken;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.vehiculos.utils.Constants.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class CarController {
    @Autowired
    private final CarServiceImp carServiceImp;

    @Autowired
    private final ValidateToken validateToken;
    private ApiResponse apiResponse;

    @SecurityRequirement(name = "jwt")
    @PostMapping("")
    public ResponseEntity<ApiResponse> saveCar(HttpServletRequest request, @RequestBody CarRequestDto car) {
        validateToken.isValid(request);
        carServiceImp.createCar(car);
        apiResponse = new ApiResponse(REGISTER_CREATED, "");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "jwt")
    @GetMapping
    public ResponseEntity<ApiResponse> getAllCar(HttpServletRequest request) {
        validateToken.isValid(request);
        apiResponse = new ApiResponse(REGISTER_LIST, carServiceImp.allCar());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @SecurityRequirement(name = "jwt")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findCarById(HttpServletRequest request, @PathVariable Long id) {
        validateToken.isValid(request);
        apiResponse = new ApiResponse(REGISTER_FOUND, carServiceImp.getCar(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @SecurityRequirement(name = "jwt")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCar(HttpServletRequest request, @PathVariable Long id, @RequestBody Car car) {

        validateToken.isValid(request);
        carServiceImp.updateCar(id, car);
        apiResponse = new ApiResponse(REGISTER_UPDATED, "");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);


    }
}
