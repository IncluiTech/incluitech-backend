package com.ages.incuitech.backend.chatbotservice.business.service;

import com.ages.incuitech.backend.chatbotservice.business.domain.*;
import org.springframework.http.*;
import org.springframework.web.client.*;

public class FacebookService {
    private static final String CAMPOS = "?fields=first_name,last_name,profile_pic,locale";
    private RestTemplate template;
    private String graphURL;
    private String accessToken;

    public FacebookService(RestTemplate template, String graphURL, String accessToken) {
        this.template = template;
        this.graphURL = graphURL;
        this.accessToken = accessToken;
    }

    public FacebookProfile getProfile(String userId) {
        String url = this.graphURL + userId + CAMPOS + "&access_token=" + this.accessToken;
        ResponseEntity<FacebookProfile> response = this.template.getForEntity(url, FacebookProfile.class);
        return response.getBody();
    }
}
