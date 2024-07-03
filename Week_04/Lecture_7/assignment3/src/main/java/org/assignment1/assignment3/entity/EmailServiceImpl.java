package org.assignment1.assignment3.entity;

import org.assignment1.assignment3.services.EmailService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("emailService")
@Scope("singleton") // Singleton scope (default scope)
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(String from, String to, String subject, String body) {
        System.out.println("From : " + from);
        System.out.println("To : " + to);
        System.out.println("Subject : " + subject);
        System.out.println("Body : " + body);
        System.out.println("email sent ...");
        System.out.println("=========================");
    }
}

