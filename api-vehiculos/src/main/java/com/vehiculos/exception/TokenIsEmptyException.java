package com.vehiculos.exception;

public class TokenIsEmptyException extends RuntimeException{
    public TokenIsEmptyException(String msg){
        super(msg);
    }
}
