package com.RapidGrill.request;

import com.RapidGrill.model.Address;

import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
    // private String orderStatus;
}
