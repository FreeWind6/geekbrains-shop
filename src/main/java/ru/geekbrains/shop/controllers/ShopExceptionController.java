package ru.geekbrains.shop.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import ru.geekbrains.shop.exceptions.ProductNotFoundException;

@Slf4j
@ControllerAdvice
public class ShopExceptionController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFoundException(final ProductNotFoundException ex) {
        log.error("Product not found thrown", ex);
        return "error";
    }

}