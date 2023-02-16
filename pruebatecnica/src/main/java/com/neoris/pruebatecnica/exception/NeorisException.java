package com.neoris.pruebatecnica.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NeorisException extends RuntimeException {

    private HttpStatus status;

    public NeorisException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
