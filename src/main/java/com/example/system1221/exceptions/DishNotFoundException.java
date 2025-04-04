package com.example.system1221.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(Long id) {
        super("Dish with id " + id + " not found");
    }
}