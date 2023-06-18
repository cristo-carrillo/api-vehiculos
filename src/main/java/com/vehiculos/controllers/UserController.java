package com.vehiculos.controllers;

import com.vehiculos.dto.UserCarResponseDto;
import com.vehiculos.models.User;
import com.vehiculos.services.UserService;
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
    @Operation(summary = "Get a user by id",
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
    public ResponseEntity<ApiResponse> findUserById(HttpServletRequest request, @PathVariable Long id) {
        validateToken.isValid(request);
        apiResponse = new ApiResponse(REGISTER_FOUND, userService.getUser(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @Operation(summary = "Add a new user",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = REGISTER_CREATED,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = REGISTER_EXIST,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "501", description = ERROR_SERVER,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class)))})
    @PostMapping("")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody User user) {
        userService.createUser(user);
        apiResponse = new ApiResponse(REGISTER_CREATED, "");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }
    @SecurityRequirement(name = "jwt")
    @Operation(summary = "List all users",
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
    @Operation(summary = "Update user",
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
    public ResponseEntity<ApiResponse> updateUser(HttpServletRequest request,@PathVariable Long id, @RequestBody User user) {
        validateToken.isValid(request);
        userService.updateUser(id, user);
        apiResponse = new ApiResponse(REGISTER_UPDATED, "");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }


}
