package com.learn.e_commerce.OrderLine;

import com.learn.e_commerce.Order.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .productId(request.productId())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .build();
    }

    public OrderLineResponse fromOrderLine(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getProductId(),
                orderLine.getQuantity()
        );
    }
}
