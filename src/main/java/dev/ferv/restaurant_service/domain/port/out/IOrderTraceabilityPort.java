package dev.ferv.restaurant_service.domain.port.out;

import dev.ferv.restaurant_service.domain.model.client.OrderTraceClient;

public interface IOrderTraceabilityPort {

    void createOrderTrace(OrderTraceClient orderTraceClient, String jwt);

}
