package org.assignment1.assignment2.Services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Setter
@Getter

@Service
public class EmployeeService {
    private String email;

    // Constructor injection with @Qualifier
    private final EmailService emailServiceConstructor;

    @Autowired
    public EmployeeService(@Qualifier("emailService") EmailService emailServiceConstructor) {
        this.emailServiceConstructor = emailServiceConstructor;
    }

    //Field injection with @Qualifier
    @Autowired
    @Qualifier("emailService")
    private EmailService emailServiceField;

    //Setter injection with @Qualifier
    private EmailService emailServiceSetter;

    @Autowired
    @Qualifier("emailService")
    public void setEmailServiceSetter(EmailService emailServiceSetter) {
        this.emailServiceSetter = emailServiceSetter;
    }
}
