package com.learn.e_commerce.notification;

import com.learn.e_commerce.Kafka.Payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification,String> {
}
