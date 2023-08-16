package com.example.MyClientApp.service;

import com.example.MyClientApp.model.Role;
import com.example.MyClientApp.model.StoreDetailsModel;
import com.example.MyClientApp.model.User;
import com.example.MyClientApp.repository.UserRepository;
import com.example.MyClientApp.response.StoreResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AdminService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    //getAllUser -
    //getCustomers -
    //getShops -
    //setShopsActive -
    //getCustomerById
    //getCustomersOrders
    //getProductsOfShops


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllCustomers() {
        return userRepository.findAllByRole(Role.CUSTOMER);
    }

    public List<User> getAllShops() {
        return userRepository.findAllByRole(Role.SHOP);
    }

//    TODO
    public StoreResponse getShopDetail(String id) throws JsonProcessingException {
        final String URL = "http://localhost:9090/store/byUserId/";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL+id, String.class);
//        try {
//            return objectMapper.readValue(responseEntity.getBody().toString(), StoreResponse.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        return objectMapper.readValue(responseEntity.getBody(), StoreResponse.class);
    }

    public void setStoreStatus(String id, boolean status) {
        final String URL = "http://localhost:9090/store/edit/status/";
        User user = userRepository.findById(id).orElseThrow();
        user.setActive(status);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(URL + id, status, String.class);
    }

}
