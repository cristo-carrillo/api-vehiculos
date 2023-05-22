package com.vehiculos.utils;

import com.vehiculos.exception.TokenIsEmptyException;
import com.vehiculos.exception.TokenIsNotValidException;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

import static com.vehiculos.utils.Constants.TOKEN_IS_EMPTY;
import static com.vehiculos.utils.Constants.TOKEN_IS_INVALID;

@RequiredArgsConstructor
public class ValidateTokenImp implements ValidateToken {

    private final JWTUtil jwtUtil;

    public void isValid(HttpServletRequest request) {
        try {
            String jwt = request.getHeader("Authorization").substring(7);
            jwtUtil.getKey(jwt);
        } catch (NullPointerException e) {
            throw new TokenIsEmptyException(TOKEN_IS_EMPTY);
        } catch (Exception e) {
            throw new TokenIsNotValidException(TOKEN_IS_INVALID);
        }
    }
}
