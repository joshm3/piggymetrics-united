package com.example;

import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.beans.Transient;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class PiggyBackTest {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:80/";

    @Test
    public void testAccountsDemo() {
        String url = BASE_URL + "accounts/demo";

        // Send the GET request
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Asserting the response
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("\"name\":\"demo\""));
    }

    //All of the other endpoints are blocked by security so this is it for now
}
