package com.thgy.helloServiceCore;

import com.thgy.common.result.ResultEnum;
import com.thgy.common.result.ResultException;
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
    public String hello() {
        return "hello world!";
    }

    @Override
    public String hello(@ApiParam("姓名") @RequestParam String name) {
        if (name.equals("0")){
            Integer a = 1/ 0;
        }
        return "hello " + name;
    }

    // 注意:　此处@RequestHeader不能省略
    @Override
    public User hello(@RequestHeader String name, @ApiParam(value = "年龄") @RequestHeader Integer age) {
        if (age == 0){
            throw new ResultException(ResultEnum.PARAMETER_ERROR, "年龄不对");
        }
        User user = new User();
        user.setAge(age.toString());
        user.setName(name);
        return user;
    }

    @Override
    public void hello(@RequestBody User user) {
    }
}
