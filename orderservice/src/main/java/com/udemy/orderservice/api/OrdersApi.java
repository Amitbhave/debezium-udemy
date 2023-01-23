package com.udemy.orderservice.api;

import com.udemy.orderservice.model.AddOrderRequest;
import com.udemy.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrdersApi {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody @Valid AddOrderRequest addOrderRequest) {
        orderService.createOrder(addOrderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
