package com.thgy.apigateway;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author box
 * @since 2018/11/6/13:34
 */

public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.remove("timestamp");
        errorAttributes.remove("stackTrace");
        errorAttributes.remove("cause");
        errorAttributes.remove("localizedMessage");
        return errorAttributes;
    }
}
