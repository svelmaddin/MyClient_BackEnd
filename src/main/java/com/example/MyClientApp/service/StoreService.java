package com.example.MyClientApp.service;

import com.example.MyClientApp.exception.CustomException;
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


import static com.example.MyClientApp.util.ErrorMessage.USER_NOT_FOUND;

@Service
public class StoreService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationService validationService;
    private final RestTemplate restTemplate;

    public StoreService(UserRepository userRepository,
                        PasswordEncoder passwordEncoder,
                        ValidationService validationService, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validationService = validationService;
        this.restTemplate = restTemplate;
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
                .role(Role.SHOP)
                .build();
        shop.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userRepository.save(shop);
        final StoreDetailsModel shopDetails = StoreDetailsModel.builder()
                .userId(user.getId())
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
        HttpEntity<StoreDetailsModel> entity = new HttpEntity<>(
                new StoreDetailsModel(request.getUserId(), request.getStoreName(), request.getCountry(), request.getCity(),
                        request.getStreet(), request.getZipcode(), request.getAddress(), request.getPhoneNumber())
        );
        restTemplate.postForEntity(URL, entity, StoreDetailsModel.class);
    }

    @Transactional
    public void setShopActiveStatus(String id, boolean status) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND + id, "userID"));
        user.setActive(status);
    }
}
