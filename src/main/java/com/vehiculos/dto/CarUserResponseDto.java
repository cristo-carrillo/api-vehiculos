package com.vehiculos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarUserResponseDto {

    private Long id;
    private String brand;
    private String model;
    private String color;
    private Integer modelYear;
    private String vin;
    private Double price;
    private Boolean availability;
    private UserDto user;

    public CarUserResponseDto(Long id, String brand, String model, String color, Integer modelYear, String vin, Double price, Boolean availability, UserDto user) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.modelYear = modelYear;
        this.vin = vin;
        this.price = price;
        this.availability = availability;
        this.user = user;
    }
}
