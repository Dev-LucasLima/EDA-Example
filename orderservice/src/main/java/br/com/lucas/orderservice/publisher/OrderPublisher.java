package br.com.lucas.orderservice.publisher;


import br.com.lucas.orderservice.dtos.OrderCreatedEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderPublisher {
    private static final String EVENT_NAME_ORDER_CREATED = "orderCreated-out-0";

    private final StreamBridge streamBridge;

    public OrderPublisher(@Autowired final StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publicOrderCreated(final OrderCreatedEventDto orderCreatedEventDto) {
        log.info("Publich event created order: {}", orderCreatedEventDto);

        streamBridge.send(EVENT_NAME_ORDER_CREATED, orderCreatedEventDto);
    }
}
