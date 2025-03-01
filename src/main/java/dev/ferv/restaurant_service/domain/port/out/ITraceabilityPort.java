package dev.ferv.restaurant_service.domain.port.out;

import dev.ferv.restaurant_service.domain.model.States;
import dev.ferv.restaurant_service.domain.model.client.OrderTraceClient;

public interface ITraceabilityPort {

    void createOrderTrace(OrderTraceClient orderTraceClient, String jwt);
    void updateOrderTrace(Long orderId, States newState, String jwt);
    void updateEmployeTrace(Long orderId, Long employeeId, String jwt);

}
