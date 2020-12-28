package com.isaproject.isaproject.psw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpClientService {
    private static final String API_URL = "http://localhost:51963/";
    private final RestTemplate restTemplate;

    @Autowired
    public HttpClientService() {
        this.restTemplate = new RestTemplate();
    }

    public boolean sendTenderOffer(String tenderString) {
        boolean result;
        try {
            ResponseEntity<?> response = restTemplate.exchange("http://localhost:57942/api/tender/offer",
                    HttpMethod.POST,new HttpEntity<>(tenderString, new HttpHeaders()), ResponseEntity.class);
            result = response.getStatusCodeValue() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
