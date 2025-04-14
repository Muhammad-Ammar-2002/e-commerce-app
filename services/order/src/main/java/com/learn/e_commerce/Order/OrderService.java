package com.learn.e_commerce.Order;

import com.learn.e_commerce.Customer.CustomerClient;
import com.learn.e_commerce.Exception.BusinessException;
import com.learn.e_commerce.Kafka.OrderConfirmation;
import com.learn.e_commerce.Kafka.OrderProducer;
import com.learn.e_commerce.OrderLine.OrderLineRequest;
import com.learn.e_commerce.OrderLine.OrderLineService;
import com.learn.e_commerce.Payment.PaymentClient;
import com.learn.e_commerce.Payment.PaymentRequest;
import com.learn.e_commerce.Product.ProductClient;
import com.learn.e_commerce.Product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;


    public Integer createOrder( OrderRequest request) {
//        log.info("Request Headers: {}", requestTemplate.headers());
        //check customer --> openFeign
            var customer=this.customerClient.findCustomerById(request.customerId())
                    .orElseThrow(()->new BusinessException("Can not create Order:: No customer exist with the provided id"));

        //purchase the products --> product-ms (RestTemplate)
            var purchasedProducts=this.productClient.purchaseProducts(request.products());

        //persist Order
            var order=this.repository.save(mapper.toOrder(request));

        //persist Order line

        for(PurchaseRequest purchaseRequest: request.products())
        {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            purchaseRequest.productId(),
                            order.getId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //todo start payment process

        var paymentRequest=new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer);
            paymentClient.requestOrderPayment(paymentRequest );

        //send the Order confirmation --> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById( Integer orderId) {
        return repository.findById(orderId).map(mapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException(String.format("No Order found with the provided ID:: %d",orderId)));
    }
}
