package com.vehiculos.resttemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarConverter {
    @JsonProperty("Car")
    private CarConsumerApiModel car;

}
