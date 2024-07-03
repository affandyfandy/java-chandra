package org.assignment1.assignment3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRequestScope() {
        ResponseEntity<String> response1 = restTemplate.getForEntity("/request", String.class);
        ResponseEntity<String> response2 = restTemplate.getForEntity("/request", String.class);

        assertThat(response1.getBody()).isNotEqualTo(response2.getBody());
    }
}
