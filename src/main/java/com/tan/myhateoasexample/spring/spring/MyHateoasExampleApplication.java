package com.tan.myhateoasexample.spring.spring;

import com.tan.myhateoasexample.spring.config.MyHateoasExampleCommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {MyHateoasExampleCommonConfig.class})
public class MyHateoasExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyHateoasExampleApplication.class, args);
  }

}
