package com.thgy.helloServiceApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("hello")
public interface HelloService {

    @GetMapping("")
    String hello();
}
