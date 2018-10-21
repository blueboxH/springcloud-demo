package com.thgy.helloServiceApi;

import org.springframework.web.bind.annotation.*;

public interface HelloService {

    @GetMapping("hello")
    String hello();

    // 注意: @RequestParam("name") 中的"name" 并不能像在Controller中一样省略, 否则 Feign中会报错
    @GetMapping("hello1")
    String hello(@RequestParam("name") String name);

    @GetMapping("hello2")
    User hello(@RequestHeader("name") String name, @RequestHeader("age") String age);

    @PostMapping("hello3")
    String hello(@RequestBody User user);
}
