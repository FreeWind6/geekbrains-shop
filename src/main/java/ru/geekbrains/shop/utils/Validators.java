package ru.geekbrains.shop.utils;

import java.util.UUID;

public class Validators {
    public static boolean isUUID(String string) {
        try {
            UUID.fromString(string);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
}