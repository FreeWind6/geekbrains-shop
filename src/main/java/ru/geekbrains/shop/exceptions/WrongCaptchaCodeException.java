package ru.geekbrains.shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongCaptchaCodeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WrongCaptchaCodeException(String message){
        super(message);
    }

}