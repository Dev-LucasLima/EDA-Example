package br.com.lucas.orderservice.service;

import br.com.lucas.orderservice.dtos.OrderCreatedEventDto;
import br.com.lucas.orderservice.dtos.OrderRequestDto;
import br.com.lucas.orderservice.publisher.OrderPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderPublisher orderPublisher;
//    private final OrderRepository repository; JPA repository

    public OrderService(@Autowired final OrderPublisher orderPublisher) {
        this.orderPublisher = orderPublisher;
    }

    @Transactional
    public void placeOrder(final OrderRequestDto orderRequestDto) {
        // final String orderId = repository.createOrder(orderRequest); get UUID from repository
        final String orderId = UUID.randomUUID().toString();

        final OrderCreatedEventDto orderCreatedEventDto = new OrderCreatedEventDto(
                orderId,
                orderRequestDto.customerId(),
                orderRequestDto.totalValue(),
                LocalDateTime.now()
        );

        orderPublisher.publicOrderCreated(orderCreatedEventDto);
    }
}
