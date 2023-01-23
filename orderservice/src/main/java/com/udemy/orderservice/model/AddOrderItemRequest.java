package com.udemy.orderservice.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddOrderItemRequest {

    @NotNull
    private String productId;
    @NotNull
    private int quantity;
    @NotNull
    private BigDecimal price;

}
