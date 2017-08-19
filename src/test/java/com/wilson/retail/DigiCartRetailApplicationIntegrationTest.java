package com.wilson.retail;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DigiCartRetailApplicationIntegrationTest {

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void givenUrl_whenReceivedArticles_thenSuccess() throws InterruptedException {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/users", String.class);

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

}


