package com.thgy.helloServiceCore;

import com.thgy.helloServiceApi.HelloService;
import com.thgy.helloServiceApi.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloServiceContorller implements HelloService {
    @Override
    public String hello() {
        return "hello world!";
    }

    @Override
    public String hello(@RequestParam String name) {
        return "hello " + name;
    }

    // 注意:　此处@RequestHeader不能省略
    @Override
    public User hello(@RequestHeader String name, @RequestHeader String age) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return user;
    }

    @Override
    public String hello(@RequestBody User user) {
        return "hello " + user.getName() + ", " + user.getAge();
    }
}
