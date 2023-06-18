package com.vehiculos.controllers;

import com.vehiculos.dto.UserCarResponseDto;
import com.vehiculos.models.User;
import com.vehiculos.services.UserService;
import com.vehiculos.utils.ApiResponse;
import com.vehiculos.utils.ValidateToken;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.vehiculos.utils.Constants.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @Autowired
    private final ValidateToken validateToken;
    private ApiResponse apiResponse;

    @SecurityRequirement(name = "jwt")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findUserById(HttpServletRequest request, @PathVariable Long id) {
        validateToken.isValid(request);
        apiResponse = new ApiResponse(REGISTER_FOUND, userService.getUser(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody User user) {
        userService.createUser(user);
        apiResponse = new ApiResponse(REGISTER_CREATED, "");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }
    @SecurityRequirement(name = "jwt")
    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllUser(HttpServletRequest request) {
        validateToken.isValid(request);
        List<UserCarResponseDto> registeredUsers = userService.allUser();
        if (registeredUsers.isEmpty()) {
            apiResponse = new ApiResponse(REGISTER_EMPTY, "");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        apiResponse = new ApiResponse(REGISTER_LIST, registeredUsers);
        return ResponseEntity.ok(apiResponse);
    }

    @SecurityRequirement(name = "jwt")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(HttpServletRequest request,@PathVariable Long id, @RequestBody User user) {
        validateToken.isValid(request);
        userService.updateUser(id, user);
        apiResponse = new ApiResponse(REGISTER_UPDATED, "");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }


}
