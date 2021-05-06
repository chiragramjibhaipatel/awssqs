package com.aws.controller;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MailController {

    @Autowired
    private SendGrid sendGrid;

    @GetMapping("/sendgrid")
    public String sendEmailWithSendGrid(@RequestParam("msg") String message) {

        Email from = new Email("chiragramjibhaipatel@gmail.com");
        String subject = "Hello World!";
        Email to = new Email("cecrp.vvp@gmail.com");
        Content content = new Content("text/html", "I'm replacing the <strong>body tag</strong>" + message);

        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        Response response = null;


        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            response = sendGrid.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return "email was successfully send";
    }
}