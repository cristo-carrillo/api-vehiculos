package com.vehiculos.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CarRestTemplate {
    CarRestTemplateModel getCarApiPublic(Long id) throws JsonProcessingException;
}
