package com.example.MyClientApp.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateShopRequest {
    private String name;
    private String surname;
    private String password;
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    private String email;
    private String confirmPas;

    private String storeName;
    private String country;
    private String city;
    private String street;
    private String zipcode;
    private String address;


}
