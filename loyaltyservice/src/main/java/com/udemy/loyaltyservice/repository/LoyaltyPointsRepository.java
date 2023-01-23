package com.udemy.loyaltyservice.repository;

import com.udemy.loyaltyservice.entity.LoyaltyPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoyaltyPointsRepository extends MongoRepository<LoyaltyPoint, String> {

    Optional<LoyaltyPoint> findByUserId(String userId);

}
