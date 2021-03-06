package com.thgy.consumer;

import com.thgy.helloServiceApi.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("hello-service")
interface HelloServiceFeign extends HelloService {
}
