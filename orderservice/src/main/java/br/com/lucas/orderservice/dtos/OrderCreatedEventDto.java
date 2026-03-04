package br.com.lucas.orderservice.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderCreatedEventDto(
        String orderId,
        String customerId,
        BigDecimal totalValue,
        LocalDateTime createdAt
) {

}
