package ru.geekbrains.shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class UnsupportedMediaTypeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnsupportedMediaTypeException(String message){
        super(message);
    }

}