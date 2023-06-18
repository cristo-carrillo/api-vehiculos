package com.vehiculos.controllers;

import com.vehiculos.dto.UserAuthDto;
import com.vehiculos.services.UserService;
import com.vehiculos.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.vehiculos.utils.Constants.USER_LOGIN;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    Map<String, String> data = new HashMap<>();

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody UserAuthDto user) {
        data.put("Token", userService.login(user));
        ApiResponse apiResponse = new ApiResponse(USER_LOGIN, data);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
}