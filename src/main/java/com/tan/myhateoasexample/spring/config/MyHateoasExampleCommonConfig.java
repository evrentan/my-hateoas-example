package com.tan.myhateoasexample.spring.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = "com.tan.myhateoasexample")
@EnableMongoRepositories(value = "com.tan.myhateoasexample.repository")
@EntityScan(value = "com.tan.myhateoasexample.entity")
public class MyHateoasExampleCommonConfig {
}
