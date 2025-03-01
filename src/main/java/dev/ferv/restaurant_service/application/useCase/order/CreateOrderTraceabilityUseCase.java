package dev.ferv.restaurant_service.application.useCase.order;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.client.OrderTraceClient;
import dev.ferv.restaurant_service.domain.port.in.order.ICreateOrderTraceabilityUseCase;
import dev.ferv.restaurant_service.domain.port.out.ITraceabilityPort;

@Component
public class CreateOrderTraceabilityUseCase implements ICreateOrderTraceabilityUseCase{

    private final ITraceabilityPort orderTraceabilityPort;
 
    public CreateOrderTraceabilityUseCase(ITraceabilityPort orderTraceabilityPort) {
        this.orderTraceabilityPort = orderTraceabilityPort;
    }

    @Override
    public void createOrderTraceability(OrderTraceClient orderTraceClient, String jwtToken) {
        orderTraceabilityPort.createOrderTrace(orderTraceClient, jwtToken);
    }

}
