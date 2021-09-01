package com.ejemplo.sprint5.sprint5.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;


@Configuration
@Profile("default")
public class JavaDefaultConfig {

    @Value("${spring.message}")
    private String message;


    @PostConstruct
    public void test(){
        System.out.println(message);
    }


}
