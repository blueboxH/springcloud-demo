package com.thgy.helloServiceCore;

import com.thgy.helloServiceApi.HelloService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloServiceContorller implements HelloService {
    @Override
    public String hello() {
        return "hello world!";
    }
}
