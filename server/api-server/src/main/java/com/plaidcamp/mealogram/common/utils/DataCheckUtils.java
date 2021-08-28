package com.plaidcamp.mealogram.common.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class DataCheckUtils {
    private final String regex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

    public boolean emailRegexCheck(String email) {
        return Pattern.matches(regex, email);
    }
}
