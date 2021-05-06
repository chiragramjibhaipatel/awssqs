package com.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;

@SpringBootApplication (exclude = {ContextStackAutoConfiguration.class})
public class AwssqsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwssqsApplication.class, args);
    }

}
