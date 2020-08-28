package ru.geekbrains.shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends Exception {

    private static final long serialVUID = 1L;

    public ProductNotFoundException(String message) {
        super(message);
    }

}