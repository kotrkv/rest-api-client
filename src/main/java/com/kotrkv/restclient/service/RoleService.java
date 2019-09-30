package com.kotrkv.restclient.service;

import com.kotrkv.restclient.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final String URL_ALL_ROLES = "http://localhost:8080/api/users/roles/";
    private final String URL_ROLE_BY_ID = "http://localhost:8080/api/users/roles/";

    public RoleService() {
    }

    @Autowired
    private RestTemplate restTemplate;

    public List<Role> findAll() {
        return Arrays.asList(restTemplate.getForObject(URL_ALL_ROLES, Role[].class));
    }

    public Optional<Role> findById(Integer id) {
        return Optional.of(restTemplate.getForObject(URL_ROLE_BY_ID + id, Role.class));
    }
}
