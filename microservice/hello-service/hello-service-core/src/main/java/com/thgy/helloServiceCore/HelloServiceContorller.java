package com.thgy.helloServiceCore;

import com.thgy.helloServiceApi.HelloService;
import com.thgy.helloServiceApi.User;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class HelloServiceContorller implements HelloService {
    @Override
    public String hello(MultipartFile image) {
        return "hello world!";
    }

    @Override
    public String hello(@ApiParam("姓名") @RequestParam String name) {
        return "hello " + name;
    }

    // 注意:　此处@RequestHeader不能省略
    @Override
    public User hello(@RequestHeader String name, @ApiParam(value = "年龄") @RequestHeader Integer age) {
        User user = new User();
        user.setAge(age.toString());
        user.setName(name);
        return user;
    }

    @Override
    public String hello(@RequestBody User user) {
        return "hello " + user.getName() + ", " + user.getAge();
    }
}
