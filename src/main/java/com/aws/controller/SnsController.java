package com.aws.controller;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SnsController {

    @Autowired
    private AmazonSNSClient amazonSNSClient;

    @Value("${cloud.aws.topic-arn}")
    private String topicArn;

    @GetMapping("/addSubscription/{email}")
    public String addSubscription(@PathVariable String email){
        SubscribeRequest request = new SubscribeRequest(topicArn, "email", email);
        amazonSNSClient.subscribe(request);
        return "Subscription Done";
    }

    @GetMapping("sendNotification")
    public String publishMessageToTopic(){
        PublishRequest request = new PublishRequest(topicArn, "Detail of the message", "Notification: header");
        amazonSNSClient.publish(request);
        return "Notified";
    }

}
