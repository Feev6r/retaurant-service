package dev.ferv.restaurant_service.application.useCase.order;

import org.springframework.stereotype.Component;
import dev.ferv.restaurant_service.domain.port.in.order.ICancelOrderUseCase;
import dev.ferv.restaurant_service.domain.service.IOrderDomainService;

@Component
public class CancelOrderUseCase implements ICancelOrderUseCase{

    private final IOrderDomainService orderService;    

    public CancelOrderUseCase(IOrderDomainService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void cancelOrder(Long orderId) {
        orderService.cancelOrder(orderId);
    }

}
