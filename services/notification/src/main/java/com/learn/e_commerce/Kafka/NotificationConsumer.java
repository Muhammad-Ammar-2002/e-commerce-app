package com.learn.e_commerce.Kafka;

import com.learn.e_commerce.Email.EmailService;
import com.learn.e_commerce.Kafka.Order.OrderConfirmation;
import com.learn.e_commerce.Kafka.Payment.PaymentNotificationRequest;
import com.learn.e_commerce.notification.Notification;
import com.learn.e_commerce.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.learn.e_commerce.notification.NotificationType.ORDER_CONFIRMATION;
import static com.learn.e_commerce.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentNotificationRequest paymentNotificationRequest) throws MessagingException {
        log.info(format("Consuming message from payment topic ::%s", paymentNotificationRequest));
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentNotificationRequest(paymentNotificationRequest)
                        .build()
        );
        // todo send email
        var customerName= paymentNotificationRequest.customerFirstname()+" "+ paymentNotificationRequest.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentNotificationRequest.customerEmail(),
                customerName,
                paymentNotificationRequest.amount(),
                paymentNotificationRequest.orderReference()
        );

    }
    @KafkaListener(topics = "order-topic")
    public void consumePaymentSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming message from order topic ::%s",orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        // todo send email
        var customerName=orderConfirmation.customer().firstname()+" "+orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationSuccessEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products());
    }







}
