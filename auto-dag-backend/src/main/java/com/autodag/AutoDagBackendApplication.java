package com.autodag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AutoDagBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoDagBackendApplication.class, args);
    }

}