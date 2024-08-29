package com.gethealthy.illnessrecordservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IllnessRecordServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IllnessRecordServiceApplication.class, args);
    }

}
