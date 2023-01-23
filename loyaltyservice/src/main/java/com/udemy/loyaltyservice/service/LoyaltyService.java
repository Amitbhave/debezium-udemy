package com.udemy.loyaltyservice.service;

import com.udemy.loyaltyservice.entity.LoyaltyPoint;
import com.udemy.loyaltyservice.entity.LoyaltyPointLog;
import com.udemy.loyaltyservice.model.OrderEvent;
import com.udemy.loyaltyservice.repository.LoyaltyPointLogRepository;
import com.udemy.loyaltyservice.repository.LoyaltyPointsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoyaltyService {

    private final LoyaltyPointsRepository loyaltyPointsRepository;
    private final LoyaltyPointLogRepository loyaltyPointLogRepository;

    @Transactional
    public void addLoyaltyPointsForOrder(OrderEvent order) {
        BigDecimal totalOrderPrice = order.getPayload().getTotalPrice();
        int loyaltyPointsForCurrentOrder = totalOrderPrice.intValue() / 10;
        String userId = order.getPayload().getUserId();

        Optional<LoyaltyPoint> optionalLoyaltyPoint = getLoyaltyPointsForUser(userId);
        if (optionalLoyaltyPoint.isEmpty()) {
            saveLoyaltyPointForUser(loyaltyPointsForCurrentOrder, userId);
        } else {
            LoyaltyPoint loyaltyPointForUser = optionalLoyaltyPoint.get();
            updateLoyaltyPointForUser(loyaltyPointsForCurrentOrder, loyaltyPointForUser);
        }
        saveLoyaltyPointLog(order, loyaltyPointsForCurrentOrder);
    }

    private Optional<LoyaltyPoint> getLoyaltyPointsForUser(String userId) {
        return loyaltyPointsRepository.findByUserId(userId);
    }

    private void saveLoyaltyPointForUser(int loyaltyPointsForCurrentOrder, String userId) {
        LoyaltyPoint loyaltyPoint = LoyaltyPoint.builder()
                .value(loyaltyPointsForCurrentOrder)
                .userId(userId)
                .build();
        loyaltyPointsRepository.save(loyaltyPoint);
    }

    private void updateLoyaltyPointForUser(int loyaltyPointsForCurrentOrder, LoyaltyPoint loyaltyPoint) {
        loyaltyPoint.setValue(loyaltyPoint.getValue() + loyaltyPointsForCurrentOrder);
        loyaltyPointsRepository.save(loyaltyPoint);
    }

    private void saveLoyaltyPointLog(OrderEvent order, int loyaltyPointsForCurrentOrder) {
        LoyaltyPointLog loyaltyPointLog = LoyaltyPointLog.builder()
                .orderId(order.getPayload().getId())
                .totalPrice(order.getPayload().getTotalPrice())
                .userId(order.getPayload().getUserId())
                .value(loyaltyPointsForCurrentOrder)
                .build();
        loyaltyPointLogRepository.save(loyaltyPointLog);
    }


}
