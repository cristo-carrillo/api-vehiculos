package com.vehiculos.configuration;

import com.vehiculos.resttemplate.CarRestTemplate;
import com.vehiculos.resttemplate.CarRestTemplateImp;
import com.vehiculos.utils.JWTUtil;
import com.vehiculos.utils.ValidateToken;
import com.vehiculos.utils.ValidateTokenImp;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final JWTUtil jwtUtil;
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public CarRestTemplate carRest(){
        return new CarRestTemplateImp(new RestTemplate());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ValidateToken validateToken(){return new ValidateTokenImp(jwtUtil); }
}
