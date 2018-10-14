package com.thgy.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    HelloServiceFeign helloServiceFeign;

    @GetMapping("hello")
    public String hello(){
        return helloServiceFeign.hello();
    }
}
