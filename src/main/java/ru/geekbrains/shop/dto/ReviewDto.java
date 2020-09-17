package ru.geekbrains.shop.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ReviewDto {
    private String captchaCode;
    private String commentary;
    private UUID productId;
}