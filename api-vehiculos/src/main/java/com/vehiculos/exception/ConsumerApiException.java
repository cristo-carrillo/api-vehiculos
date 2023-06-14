package com.vehiculos.exception;

public class ConsumerApiException extends RuntimeException{
    public ConsumerApiException(String msg){
        super(msg);
    }
}
