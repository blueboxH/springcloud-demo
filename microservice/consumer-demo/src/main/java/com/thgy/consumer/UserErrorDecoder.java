package com.thgy.consumer;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import java.io.IOException;


public class UserErrorDecoder implements ErrorDecoder {

    public Exception decode(String methodKey, Response response) {
//        ObjectMapper om = new ObjectMapper();
        Object resEntity;
        Exception exception = null;
        try {
            resEntity = JSON.parse(Util.toString(response.body().asReader()));
//            resEntity = om.readValue(), JSON.class);
        //为了说明我使用的 WebApplicationException 基类，去掉了封装
            exception = new WebApplicationException(javax.ws.rs.core.Response.status(response.status()).entity(resEntity).type(MediaType.APPLICATION_JSON).build());
        } catch (IOException ex) {
            System.out.println("************************");
            System.out.println(ex.getMessage());
            System.out.println(ex);
            System.out.println("************************");

        }
        // 这里只封装4开头的请求异常
        if (400 <= response.status() || response.status() < 500){
            exception = new HystrixBadRequestException("request exception wrapper", exception);
        }else{
            System.out.println("************************");
            System.out.println(exception.getMessage());
            System.out.println(exception);
            System.out.println("************************");

        }
        return exception;
    }
}
