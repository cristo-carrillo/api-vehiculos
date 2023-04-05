package com.vehiculos.exception;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String msgErr){
        super(msgErr);
    }
}
