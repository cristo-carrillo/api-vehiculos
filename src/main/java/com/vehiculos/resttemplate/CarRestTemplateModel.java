package com.vehiculos.resttemplate;

import lombok.Data;

@Data
public class CarRestTemplateModel {

    private Long id;
    private String car;
    private String car_model;
    private String car_color;
    private int car_model_year;
    private String car_vin;
    private String price;
    private boolean availability;
}
