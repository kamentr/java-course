package com.java.course.controller;

import java.io.IOException;

import com.java.course.model.TwitterUser;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/twitter")
public class TwitterController {

    @GetMapping("/user/{username}")
    TwitterUser getUserByUsername(@PathVariable("username") String username) {
        return getUser(username);
    }

    public TwitterUser getUser(String username) {
        String url = "https://twitter-v24.p.rapidapi.com/user/details?username=" + username;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "38d47a0db9msh4a68fc8042f3636p13fac6jsn9d0695e7667b");
        headers.set("X-RapidAPI-Host", "twitter-v24.p.rapidapi.com");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TwitterUser> response = restTemplate.exchange(url, HttpMethod.GET, entity, TwitterUser.class);
        return response.getBody();
    }
}
