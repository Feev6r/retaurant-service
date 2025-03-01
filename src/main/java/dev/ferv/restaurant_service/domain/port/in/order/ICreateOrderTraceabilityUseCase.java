package dev.ferv.restaurant_service.domain.port.in.order;

import dev.ferv.restaurant_service.domain.model.client.OrderTraceClient;

public interface ICreateOrderTraceabilityUseCase {

    void createOrderTraceability(OrderTraceClient orderTraceClient, String jwtToken);
}
