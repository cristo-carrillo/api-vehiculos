package com.vehiculos.utils;

import com.vehiculos.exception.TokenIsNotValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static com.vehiculos.utils.Constants.MESSAGE;
import static com.vehiculos.utils.Constants.STATUS;

@RequiredArgsConstructor
public class ValidateTokenImp implements ValidateToken{

    private final JWTUtil jwtUtil;
    public void isValid(String jwt){
        try {
            jwtUtil.getKey(jwt);
        }catch (Exception e){
            System.out.println(jwt+"\n"+e.getMessage()+"\n"+e.getClass());
            throw new TokenIsNotValidException("El token es invalido");
        }
    }
}
