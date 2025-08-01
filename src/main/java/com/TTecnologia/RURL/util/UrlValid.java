package com.TTecnologia.RURL.util;

import org.apache.commons.validator.routines.UrlValidator;

public class UrlValid {

    public static boolean isValidURL(String url){
        try{
            UrlValidator validator = new UrlValidator(new String[]{"http", "https"});
            return validator.isValid(url);
        }
        catch (Exception e){
            return false;
        }
    }
}
