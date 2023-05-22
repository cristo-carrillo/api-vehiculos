package com.vehiculos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private String color;
    private Integer modelYear;
    private String vin;
    private Double price;
    private Boolean availability;

    public CarDto(Long id, String brand, String model,
                  String color, Integer modelYear, String vin, Double price, Boolean availability) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.modelYear = modelYear;
        this.vin = vin;
        this.price = price;
        this.availability = availability;
    }
}
