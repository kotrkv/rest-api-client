package com.kotrkv.restclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kotrkv.restclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final String URL_ALL_USERS = "http://localhost:8080/api/users/";
    private final String URL_USER_BY_ID = "http://localhost:8080/api/users/";
    private final String URL_ADD_USER = "http://localhost:8080/api/users/";
    private final String URL_UPDATE_USER = "http://localhost:8080/api/users/";

    @Autowired
    private RestTemplate restTemplate;

    public UserService() {

    }

    public List<User> findAll() {
        return Arrays.asList(restTemplate.getForObject(URL_ALL_USERS, User[].class));
    }

    public void add(User user) {
        restTemplate.postForEntity(URL_ADD_USER, user, User.class);
    }

    public Optional<User> findById(Integer id) {
        return Optional.of(restTemplate.getForObject(URL_USER_BY_ID + id, User.class));
    }

    public void update(User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
        restTemplate.exchange(URL_UPDATE_USER, HttpMethod.PUT, httpEntity, User.class);
    }

    public void delete(Integer id) {

    }
}
