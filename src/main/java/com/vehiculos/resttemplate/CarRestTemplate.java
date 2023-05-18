package com.vehiculos.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CarRestTemplate {
    CarConsumerApiModel getCarApiPublic(Long id) throws JsonProcessingException;
}
