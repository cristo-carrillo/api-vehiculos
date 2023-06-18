package com.vehiculos.configuration;

import com.vehiculos.exception.*;
import com.vehiculos.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleAlreadyExistsException(AlreadyExistsException alreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.builder()
                        .message(alreadyExistsException.getMessage())
                        .data("")
                        .build());
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ApiResponse> handleCarNotFoundException(CarNotFoundException carNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.builder()
                        .message(carNotFoundException.getMessage())
                        .data("")
                        .build());
    }

    @ExceptionHandler(CarUpdateException.class)
    public ResponseEntity<ApiResponse> handleCarUpdateException(CarUpdateException carUpdateException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.builder()
                        .message(carUpdateException.getMessage())
                        .data("")
                        .build());
    }

    @ExceptionHandler(ConsumerApiException.class)
    public ResponseEntity<ApiResponse> handleConsumerApiException(ConsumerApiException consumerApiException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.builder()
                        .message(consumerApiException.getMessage())
                        .data("")
                        .build());
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<ApiResponse> handlePasswordIncorrectException(PasswordIncorrectException passwordIncorrectException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.builder()
                        .message(passwordIncorrectException.getMessage())
                        .data("")
                        .build());
    }

    @ExceptionHandler(TokenIsEmptyException.class)
    public ResponseEntity<ApiResponse> handleTokenIsEmptyException(TokenIsEmptyException tokenIsEmptyException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.builder()
                        .message(tokenIsEmptyException.getMessage())
                        .data("")
                        .build());
    }
    @ExceptionHandler(TokenIsNotValidException.class)
    public ResponseEntity<ApiResponse> handleTokenIsEmptyException(TokenIsNotValidException tokenIsNotValidException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.builder()
                        .message(tokenIsNotValidException.getMessage())
                        .data("")
                        .build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.builder()
                        .message(userNotFoundException.getMessage())
                        .data("")
                        .build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleUserNotFoundException(RuntimeException runtimeException) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(ApiResponse.builder()
                        .message(runtimeException.getMessage())
                        .data("")
                        .build());
    }


}
