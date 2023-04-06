package com.example.product.uiti;

import java.util.regex.Pattern;

public class Regex {
    public static final String REGEX_NAME = "^([A-ZÀ-ỹ][a-zÀ-ỹ]*[ ]?)+$";
    public static final String REGEX_DESCRIPTION = "^[A-Za-z][A-Za-z0-9_ ]{7,19}$";
    public static final String REGEX_QUANTITY = "^[1-9][0-9]{1,6}$";
    public static final String REGEX_PRICE = "^-?\\d+[,.?\\d+]*$";


    public static boolean isName(String name){
        return Pattern.matches(REGEX_NAME,name);
    }
    public static boolean isDescription(String description){
        return Pattern.matches(REGEX_DESCRIPTION,description);
    }
    public static boolean isQuantity(String quantity){
        return Pattern.matches(REGEX_QUANTITY,quantity);
    }
    public static boolean isPrice(String price){
        return Pattern.matches(REGEX_PRICE,price);
    }

}
