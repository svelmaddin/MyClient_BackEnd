package com.example.MyClientApp.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateStoreDetailsRequest {
    private String storeName;
    private String country;
    private String city;
    private String street;
    private String zipcode;
    private String address;
    private String phoneNumber;
}
