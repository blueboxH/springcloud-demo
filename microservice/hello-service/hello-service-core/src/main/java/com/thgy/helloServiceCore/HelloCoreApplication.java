package com.thgy.helloServiceCore;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableSwagger2Doc
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class HelloCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloCoreApplication.class, args);
    }

    @Bean
    public DefaultErrorAttributes errorAttributes(){
        return new MyErrorAttributes();
    }
}
