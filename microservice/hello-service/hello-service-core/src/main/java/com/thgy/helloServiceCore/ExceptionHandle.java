package com.thgy.helloServiceCore;

import com.thgy.common.result.ResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 全局异常处理
 *
 * @author box
 * @since 2018/7/4/9:23
 */

@ControllerAdvice
public class ExceptionHandle {



    private static final Logger log = LoggerFactory.getLogger(ExceptionHandle.class);

//    @ExceptionHandler(value = Throwable.class)
//    @ResponseBody
//    public Throwable handle(Throwable e, HttpServletResponse response) {
//        if (e instanceof ResultException) {
//            response.setStatus(Integer.parseInt(((ResultException) e).getCode()));
//        } else  {
//            response.setStatus(-1);
//        }
//        return e;
//    }

//    @ExceptionHandler(value = Throwable.class)
//    @ResponseBody
//    public Map handle(Throwable e, HttpServletResponse response) {
//        Map map = new HashMap();
//        if (e instanceof ResultException) {
//            map.put("status", ((ResultException) e).getCode());
//        } else  {
//            map.put("status", "-1");
//        }
//        map.put("message", e.getMessage());
//        map.put("error", e.getCause());
//        return map;
//    }

}

