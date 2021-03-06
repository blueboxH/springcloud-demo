package com.thgy.helloServiceCore;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableSwagger2Doc
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class HelloCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloCoreApplication.class, args);
    }
}
