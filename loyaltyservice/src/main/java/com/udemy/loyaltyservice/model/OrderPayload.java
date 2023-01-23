package com.udemy.loyaltyservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderPayload {

    @JsonProperty("_id")
    private String id;
    private List<OrderItem> orderItems = new ArrayList<>();
    private OrderStatus status;
    private BigDecimal totalPrice;
    private String userId;

}
