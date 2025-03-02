package dev.ferv.restaurant_service.domain.port.in.order;

import dev.ferv.restaurant_service.domain.model.States;

public interface IUpdateOrderTraceabilityUseCase {

    void updateOrderTrace(Long orderId, Long employeeId, States newState, String jwtToken);
}
