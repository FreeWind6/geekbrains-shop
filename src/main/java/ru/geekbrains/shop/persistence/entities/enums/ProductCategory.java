package ru.geekbrains.shop.persistence.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProductCategory {

    DRINK("Напитки"),
    FOOD("Продукты");

    @Getter
    private String name;

}