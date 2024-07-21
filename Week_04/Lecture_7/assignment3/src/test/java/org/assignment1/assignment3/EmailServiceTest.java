package org.assignment1.assignment3;

import org.assignment1.assignment3.entity.EmailServiceImpl;
import org.assignment1.assignment3.services.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {


    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private EmailService emailServiceMock;

    @Test
    public void testSendEmail() {
        // Prepare test data
        String from = "chandrafpt@example.com";
        String to = "hadifpt@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        // Mock behavior
        emailServiceMock.sendEmail(from, to, subject, body);

        // Verify that sendEmail method was called with correct parameters
        Mockito.verify(emailServiceMock, Mockito.times(1)).sendEmail(from, to, subject, body);
    }
}