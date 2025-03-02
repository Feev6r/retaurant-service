package dev.ferv.restaurant_service.application.useCase.order;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.States;
import dev.ferv.restaurant_service.domain.port.in.order.IUpdateOrderTraceabilityUseCase;
import dev.ferv.restaurant_service.domain.port.out.ITraceabilityPort;

@Component
public class UpdateOrderTraceabilityUseCase implements IUpdateOrderTraceabilityUseCase{

    private final ITraceabilityPort orderTraceabilityPort;

    
    public UpdateOrderTraceabilityUseCase(ITraceabilityPort orderTraceabilityPort) {
        this.orderTraceabilityPort = orderTraceabilityPort;
    }

    @Override
    public void updateOrderTrace(Long orderId, Long employeeId, States newState, String jwtToken) {
        orderTraceabilityPort.updateOrderTrace(orderId, employeeId, newState, jwtToken);
    }

}
