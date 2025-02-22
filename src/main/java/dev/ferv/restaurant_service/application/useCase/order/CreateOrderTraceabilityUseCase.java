package dev.ferv.restaurant_service.application.useCase.order;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.client.OrderTraceClient;
import dev.ferv.restaurant_service.domain.port.in.order.ICreateOrderTraceabilityUseCase;
import dev.ferv.restaurant_service.domain.port.out.IOrderTraceabilityPort;
import dev.ferv.restaurant_service.infrastructure.configuration.security.SecurityContext;

@Component
public class CreateOrderTraceabilityUseCase implements ICreateOrderTraceabilityUseCase{

    private final IOrderTraceabilityPort orderTraceabilityPort;
 
    public CreateOrderTraceabilityUseCase(IOrderTraceabilityPort orderTraceabilityPort) {
        this.orderTraceabilityPort = orderTraceabilityPort;
    }

    @Override
    public void createOrderTraceability(OrderTraceClient orderTraceClient) {
        orderTraceabilityPort.createOrderTrace(orderTraceClient, "Bearer " + SecurityContext.getToken());
    }

}
