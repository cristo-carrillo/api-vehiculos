package com.vehiculos.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class ApiResponse {
    private String message;
    private Object data;

}