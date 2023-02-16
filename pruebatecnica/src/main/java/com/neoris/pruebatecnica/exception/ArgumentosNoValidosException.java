package com.neoris.pruebatecnica.exception;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class ArgumentosNoValidosException {
    public static List<String> handleValidationExceptions(
            BindingResult resultRequest) {

        return resultRequest.getAllErrors().stream().map(error ->
                error.getDefaultMessage()
        ).collect(Collectors.toList());
    }
}
