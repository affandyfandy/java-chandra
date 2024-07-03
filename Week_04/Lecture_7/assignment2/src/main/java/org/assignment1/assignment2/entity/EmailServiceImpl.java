package org.assignment1.assignment2.entity;

import org.assignment1.assignment2.Services.EmailService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("To : " + to);
        System.out.println("Subject : " + subject);
        System.out.println("Body : " + body);
        System.out.println("email sent ...");
        System.out.println("=========================");

    }

}
