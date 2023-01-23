package com.udemy.orderservice.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AddOrderRequest {

    @NotNull
    private String userId;
    @NotNull
    private String addressId;
    @NotNull
    private List<AddOrderItemRequest> orderItems;

}
