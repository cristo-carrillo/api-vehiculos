package com.vehiculos.configuration;

import com.vehiculos.resttemplate.CarRestTemplate;
import com.vehiculos.resttemplate.CarRestTemplateImp;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public CarRestTemplate carRest(){
        return new CarRestTemplateImp(new RestTemplate());
    }
}