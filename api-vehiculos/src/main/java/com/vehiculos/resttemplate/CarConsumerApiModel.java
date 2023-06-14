package com.vehiculos.resttemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarConsumerApiModel {

    private Long id;
    private String car;
    @JsonProperty("car_model")
    private String carModel;
    @JsonProperty("car_color")
    private String carColor;
    @JsonProperty("car_model_year")
    private int carModelYear;
    @JsonProperty("car_vin")
    private String carVin;
    private String price;
    private boolean availability;
}
