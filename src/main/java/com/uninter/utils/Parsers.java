package com.uninter.utils;

//Parser's de dados
public class Parsers {
    //Parser do Tipo Integer
    public static Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
