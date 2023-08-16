package com.example.MyClientApp.service;

import com.example.MyClientApp.model.Gender;
import com.example.MyClientApp.model.Role;
import com.example.MyClientApp.model.StoreDetailsModel;
import com.example.MyClientApp.model.User;
import com.example.MyClientApp.repository.UserRepository;
import com.example.MyClientApp.request.CreateShopRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StoreService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationService validationService;

    public StoreService(UserRepository userRepository,
                        PasswordEncoder passwordEncoder,
                        ValidationService validationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validationService = validationService;
    }

    @Transactional
    protected void createShop(CreateShopRequest request) {
        validationService.validationCheckCreateShop(request);
        final User shop = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getEmail())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .gender(Gender.CUSTOM)
                .active(false)
                .role(Role.valueOf("SHOP"))
                .build();
        shop.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(shop);
        final StoreDetailsModel shopDetails = StoreDetailsModel.builder()
                .storeName(request.getStoreName())
                .country(request.getCountry())
                .city(request.getCity())
                .street(request.getStreet())
                .zipcode(request.getZipcode())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .build();
        createStoreDetails(shopDetails);
    }

    @Transactional
    private void createStoreDetails(StoreDetailsModel request) {
        final String URL = "http://localhost:9090/store/createStore";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<StoreDetailsModel> entity = new HttpEntity<>(
                new StoreDetailsModel(request.getStoreName(), request.getCountry(), request.getCity(),
                        request.getStreet(), request.getZipcode(), request.getAddress(), request.getPhoneNumber())
        );
        restTemplate.postForEntity(URL, entity, StoreDetailsModel.class);
    }
}
