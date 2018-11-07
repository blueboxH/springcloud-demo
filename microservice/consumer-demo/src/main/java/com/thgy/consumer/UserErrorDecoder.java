package com.thgy.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.thgy.common.result.ResultException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * 预计作为自定义feign抛出的异常, 但是不知道有没有很大的必要
 */

public class UserErrorDecoder implements ErrorDecoder {

    public Exception decode(String methodKey, Response response) {
//        ObjectMapper om = new ObjectMapper();
        JSONObject resEntity;
        Exception exception = null;
        try {
            resEntity = (JSONObject) JSONObject.parse(Util.toString(response.body().asReader()));
//            resEntity = om.readValue(), JSON.class);
        //为了说明我使用的 WebApplicationException 基类，去掉了封装
//            javax.ws.rs.core.Response.status(response.status()).entity(resEntity).type(MediaType.APPLICATION_JSON).build()
//            exception = new WebApplicationException(new Exception(resEntity.get("message").toString()), Integer.parseInt(resEntity.get("message").toString()));
            exception = new ResultException(resEntity.get("status").toString(), resEntity.get("message").toString());
//            if (400 <= response.status() || response.status() < 500){
//
//            }
        } catch (IOException ex) {
            System.out.println("************************");
            System.out.println(ex.getMessage());
            System.out.println(ex);
            System.out.println("************************");

        }
        // 这里只封装4开头的请求异常
//        if (400 <= response.status() || response.status() < 500){
//            exception = new HystrixBadRequestException(null, exception);
//        }else{
//            System.out.println("************************");
//            System.out.println(exception.getMessage());
//            System.out.println(exception);
//            System.out.println("************************");
//
//        }
        return exception;
    }
}
