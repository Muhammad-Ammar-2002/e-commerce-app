package com.learn.e_commerce.notification;

import com.learn.e_commerce.Kafka.Order.OrderConfirmation;
import com.learn.e_commerce.Kafka.Payment.PaymentNotificationRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentNotificationRequest paymentNotificationRequest;
}
