package dev.ferv.restaurant_service.application.useCase.order;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.port.in.order.ICreateOrderUseCase;
import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import dev.ferv.restaurant_service.domain.port.out.IOrderPort;

@Component
public class CreateOrderUseCase implements ICreateOrderUseCase{

    private final IOrderPort orderPort;
    private final IJwtPort jwtPort;

    public CreateOrderUseCase(IOrderPort orderPort, IJwtPort jwtPort) {
        this.orderPort = orderPort;
        this.jwtPort = jwtPort;
    }

    @Override
    public void createOrder(Order order) {

        order.setClientId(jwtPort.getIdBySecurityContext());
        orderPort.createOrder(order);
    }

}
