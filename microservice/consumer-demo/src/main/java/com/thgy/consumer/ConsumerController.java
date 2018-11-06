package com.thgy.consumer;

import com.thgy.common.result.ResultEnum;
import com.thgy.common.result.ResultException;
import com.thgy.helloServiceApi.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    HelloServiceFeign helloServiceFeign;

    @GetMapping("hello")
    public String hello(){
        User user = new User();
        user.setName("box2");
        user.setAge("23");
        StringBuilder sb = new StringBuilder();
//        sb.append(helloServiceFeign.hello()).append("\n");
        try {
            sb.append(helloServiceFeign.hello("box")).append("\n");
//            sb.append(helloServiceFeign.hello("box", 0)).append("\n");
        } catch (Exception e){
            System.out.print("#######");
            throw new ResultException(ResultEnum.SELECT_ERROR);
        }
        sb.append(helloServiceFeign.hello(user)).append("\n");
        return sb.toString();
    }
}
