package dev.ferv.restaurant_service.application.useCase.order;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.port.in.order.ICreateOrderUseCase;
import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import dev.ferv.restaurant_service.domain.service.IOrderDomainService;


@Component
public class CreateOrderUseCase implements ICreateOrderUseCase{

    private final IOrderDomainService orderDomainService;
    private final IJwtPort jwtPort;

    public CreateOrderUseCase(IOrderDomainService orderDomainService, IJwtPort jwtPort) {
        this.orderDomainService = orderDomainService;
        this.jwtPort = jwtPort;
    }


    @Override
    public Order createOrder(Order order) {
        order.setClientId(jwtPort.getIdBySecurityContext());
        return orderDomainService.createOrder(order);
    }

}
