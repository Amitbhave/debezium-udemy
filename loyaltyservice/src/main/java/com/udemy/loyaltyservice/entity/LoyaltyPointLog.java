package com.udemy.loyaltyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("loyaltypointlogs")
public class LoyaltyPointLog {

    @Id
    private String id;
    private String orderId;
    private BigDecimal totalPrice;
    private String userId;
    private int value;
    @CreatedDate
    private OffsetDateTime createdAt;

}
