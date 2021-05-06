package com.aws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SqsController {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;

    @GetMapping("/send/{message}")
    public void sendMessage(@PathVariable String message){
        queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(message).build());
    }

    @SqsListener("my-simple-queue")
    public void readSQSMessages(String message){
        System.out.printf("Message: %s", message);
    }
}
