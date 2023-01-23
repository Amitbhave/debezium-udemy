package com.udemy.orderservice.service;

import com.udemy.orderservice.entity.Order;
import com.udemy.orderservice.entity.OrderItem;
import com.udemy.orderservice.entity.OrderStatus;
import com.udemy.orderservice.model.AddOrderItemRequest;
import com.udemy.orderservice.model.AddOrderRequest;
import com.udemy.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(AddOrderRequest addOrderRequest) {
        Order order = Order.builder()
                .userId(addOrderRequest.getUserId())
                .addressId(addOrderRequest.getAddressId())
                .status(OrderStatus.ORDERED)
                .build();
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (AddOrderItemRequest addOrderItemRequest : addOrderRequest.getOrderItems()) {
            int orderedQuantity = addOrderItemRequest.getQuantity();
            BigDecimal productPrice = addOrderItemRequest.getPrice();
            OrderItem orderItem = OrderItem.builder()
                    .price(productPrice)
                    .quantity(orderedQuantity)
                    .productId(addOrderItemRequest.getProductId())
                    .build();

            BigDecimal totalPriceForProduct = productPrice.multiply(BigDecimal.valueOf(orderedQuantity));
            totalPrice = totalPrice.add(totalPriceForProduct);
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);
    }

}
