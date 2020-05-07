package com.ages.incuitech.backend.chatbotservice.business.service;

import com.ages.incuitech.backend.chatbotservice.business.domain.FacebookProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class FacebookService {
    private RestTemplate template;
    private String graphURL;
    private String accessToken;
    private static final String CAMPOS = "?fields=first_name,last_name,profile_pic,locale";

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
