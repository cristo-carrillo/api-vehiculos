package com.vehiculos.controllers;

import com.vehiculos.dto.UserCarResponseDto;
import com.vehiculos.models.User;
import com.vehiculos.services.UserService;
import com.vehiculos.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.vehiculos.utils.Constants.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;
    private ApiResponse apiResponse;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findUserById(@PathVariable Long id) {
        try {
            apiResponse = new ApiResponse(REGISTER_FOUND, userService.getUser(id));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse = new ApiResponse(REGISTER_NOT_FOUND, "");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            apiResponse = new ApiResponse(REGISTER_CREATED, "");
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            apiResponse = new ApiResponse(REGISTER_BAD, "");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllUser() {
        List<UserCarResponseDto> registeredUsers = userService.allUser();
        if (registeredUsers.isEmpty()) {
            apiResponse = new ApiResponse(REGISTER_EMPTY, "");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        apiResponse = new ApiResponse(REGISTER_LIST, registeredUsers);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @RequestBody User user) {

        try {
            userService.updateUser(id, user);
            apiResponse = new ApiResponse(REGISTER_UPDATED, "");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse = new ApiResponse(REGISTER_DATA_ERROR, "");
            return new ResponseEntity<>(apiResponse, HttpStatus.PAYMENT_REQUIRED);
        }
    }


}
