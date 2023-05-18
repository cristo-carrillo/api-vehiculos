package com.vehiculos.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
@RequiredArgsConstructor
public class CarRestTemplateImp implements CarRestTemplate{
    @Value("${car.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;
    @Override
    public CarConsumerApiModel getCarApiPublic(Long id) throws JsonProcessingException {
        ResponseEntity<CarConverter> response = restTemplate.getForEntity(baseUrl.concat(String.valueOf(id)), CarConverter.class);
        return response.getBody().getCar();
    }
}
