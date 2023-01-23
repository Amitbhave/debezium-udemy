package com.udemy.loyaltyservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.loyaltyservice.model.OrderEvent;
import com.udemy.loyaltyservice.service.LoyaltyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConsumer {

    public static final String CREATE_OPERATION = "c";
    private final ObjectMapper mapper;
    private final LoyaltyService loyaltyService;

    @KafkaListener(topics = "${spring.kafka.consumer.order.topicName}",
            groupId = "${spring.kafka.consumer.order.groupId}",
            containerFactory = "orderKafkaListenerContainerFactory")
    public void consume(String event, Acknowledgment ack,
                        @Header("__op") String op) throws JsonProcessingException {
        log.info("Order event received: {}, for operation: {}", event, op);

        if (CREATE_OPERATION.equals(op)) {
            OrderEvent order = mapper.readValue(event, OrderEvent.class);
            loyaltyService.addLoyaltyPointsForOrder(order);
        }

        log.info("Order event consumed: {}", event);
        ack.acknowledge();
    }

}
