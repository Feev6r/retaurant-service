package dev.ferv.restaurant_service.application.useCase.order;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.States;
import dev.ferv.restaurant_service.domain.port.in.order.IUpdateOrderStateUseCase;
import dev.ferv.restaurant_service.domain.service.IOrderDomainService;

@Component
public class UpdateOrderStateUseCase implements IUpdateOrderStateUseCase{

    private final IOrderDomainService orderDomainService;

    public UpdateOrderStateUseCase(IOrderDomainService orderService) {
        this.orderDomainService = orderService;
    }

    @Override
    public void updateOrderState(Long orderId, States state) {
        orderDomainService.updateState(orderId, state);
    }

    @Override
    public void deliverOrder(Long orderId, String pin) {
        orderDomainService.setOrderAsDelivered(orderId, pin);
    }

}
