package com.fastcampus.projectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@ConfigurationPropertiesScan
@SpringBootApplication
public class FcBoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(FcBoardApplication.class, args);
    }
        
}
