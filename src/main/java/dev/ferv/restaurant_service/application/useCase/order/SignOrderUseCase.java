package dev.ferv.restaurant_service.application.useCase.order;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.port.in.order.ISignOrderUseCase;

@Component
public class SignOrderUseCase implements ISignOrderUseCase{

    // private final IOrderPort orderPort;

    @Override
    public void signOrderByEmployeeId(Long id) {

    }

}
