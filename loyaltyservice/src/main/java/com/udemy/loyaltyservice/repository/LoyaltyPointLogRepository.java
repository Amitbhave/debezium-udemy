package com.udemy.loyaltyservice.repository;

import com.udemy.loyaltyservice.entity.LoyaltyPointLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyPointLogRepository extends MongoRepository<LoyaltyPointLog, String> {

}
