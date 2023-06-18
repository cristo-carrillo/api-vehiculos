package com.vehiculos.controllers;

import com.vehiculos.dto.CarRequestDto;
import com.vehiculos.models.Car;
import com.vehiculos.services.CarServiceImp;
import com.vehiculos.utils.ApiResponse;
import com.vehiculos.utils.ValidateToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Operation(summary = "Add a new car",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = REGISTER_CREATED,
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = TOKEN_IS_EMPTY,
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = TOKEN_IS_INVALID,
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = REGISTER_EXIST+", "+API_EMPTY,
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "501", description = ERROR_SERVER,
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)))})
    @PostMapping("")
    public ResponseEntity<ApiResponse> saveCar(HttpServletRequest request, @RequestBody CarRequestDto car) {
        validateToken.isValid(request);
        carServiceImp.createCar(car);
        apiResponse = new ApiResponse(REGISTER_CREATED, "");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "jwt")
    @Operation(summary = "List all cars",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = REGISTER_LIST,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = TOKEN_IS_EMPTY,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = TOKEN_IS_INVALID,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "501", description = ERROR_SERVER,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class)))})
    @GetMapping
    public ResponseEntity<ApiResponse> getAllCar(HttpServletRequest request) {
        validateToken.isValid(request);
        apiResponse = new ApiResponse(REGISTER_LIST, carServiceImp.allCar());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @SecurityRequirement(name = "jwt")
    @Operation(summary = "Get a car by id",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = REGISTER_FOUND,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = TOKEN_IS_EMPTY,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = TOKEN_IS_INVALID,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = REGISTER_NOT_FOUND,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "501", description = ERROR_SERVER,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findCarById(HttpServletRequest request, @PathVariable Long id) {
        validateToken.isValid(request);
        apiResponse = new ApiResponse(REGISTER_FOUND, carServiceImp.getCar(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @SecurityRequirement(name = "jwt")
    @Operation(summary = "Update car",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = REGISTER_UPDATED,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = TOKEN_IS_EMPTY,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = TOKEN_IS_INVALID,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = REGISTER_NOT_FOUND,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = REGISTER_UPDATE_ERROR,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "501", description = ERROR_SERVER,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCar(HttpServletRequest request, @PathVariable Long id, @RequestBody Car car) {

        validateToken.isValid(request);
        carServiceImp.updateCar(id, car);
        apiResponse = new ApiResponse(REGISTER_UPDATED, "");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);


    }
}
