package com.thgy.apigateway;

import com.sun.xml.internal.ws.client.ResponseContext;
import com.thgy.common.result.ResultEnum;
import com.thgy.common.result.ResultException;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用于处理最终的出口
 * @author box
 * @since 2018/11/6/13:34
 */

public class MyErrorAttributes extends DefaultErrorAttributes {

    @Autowired
    HttpServletResponse response;

//    @Override
//    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
//
//        Map<String, Object> errorAttributes = new LinkedHashMap();
//        errorAttributes.put("success", false);
//
//        Throwable cause = this.getError(webRequest).getCause();
//        // 未知错误不做处理
//
//
//        if (cause instanceof ResultException){
//
//            errorAttributes.put("errorCode", ((ResultException) cause).getCode());
//            errorAttributes.put("errorMsg", cause.getMessage());
//        } else {
//            errorAttributes.put("errorCode", "-1");
//            errorAttributes.put("errorMsg", "未知错误");
//        }
//
//        return errorAttributes;
//    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        Throwable cause = this.getError(webRequest).getCause();
        // 未知错误不做处理
        return ResultBody.error(cause).toErrorMap();
    }
}
