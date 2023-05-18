package com.vehiculos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;



}
