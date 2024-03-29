package com.uts3back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.uts3back.**"})
public class UtS3BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtS3BackApplication.class, args);
    }

}
