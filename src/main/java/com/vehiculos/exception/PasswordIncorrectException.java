package com.vehiculos.exception;

public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(String msg){
        super(msg);
    }
}
