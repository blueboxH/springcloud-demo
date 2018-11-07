package com.thgy.helloServiceCore;

import com.thgy.common.result.ResultException;
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
        Throwable error = this.getError(webRequest);
        // 未知错误不做处理
        if (error instanceof ResultException){
            errorAttributes.put("status", ((ResultException) error).getCode());
        }

        return errorAttributes;
    }
}
