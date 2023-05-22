package com.vehiculos.utils;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ValidateToken {
    void isValid(String jwt);
}
