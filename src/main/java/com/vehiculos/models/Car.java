package com.vehiculos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    private Long id;
    private String brand;
    private String model;
    private String color;
    @Column(name = "model_year")
    private Integer modelYear;
    private String vin;
    private Double price;
    private Boolean availability;

}
