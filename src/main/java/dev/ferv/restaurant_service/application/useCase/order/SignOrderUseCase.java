package dev.ferv.restaurant_service.application.useCase.order;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.port.in.order.ISignOrderUseCase;
import dev.ferv.restaurant_service.domain.service.IOrderDomainService;

@Component
public class SignOrderUseCase implements ISignOrderUseCase{

    private final IOrderDomainService orderDomainService;

    public SignOrderUseCase(IOrderDomainService orderService) {
        this.orderDomainService = orderService;
    }

    @Override
    public void signOrder(Long orderId) {
        orderDomainService.signOrder(orderId);
    }

}
