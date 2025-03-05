package dev.ferv.restaurant_service.application.useCase.order;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.model.States;
import dev.ferv.restaurant_service.domain.model.client.OrderTraceabilityClient;
import dev.ferv.restaurant_service.domain.port.in.order.IGetOrdersUseCase;
import dev.ferv.restaurant_service.domain.port.out.IOrderPort;
import dev.ferv.restaurant_service.domain.service.IOrderDomainService;

@Component
public class GetOrdersUseCase implements IGetOrdersUseCase{

    private final IOrderPort orderPort;
    private final IOrderDomainService orderDomainService;

    public GetOrdersUseCase(IOrderPort orderPort, IOrderDomainService orderDomainService) {
        this.orderPort = orderPort;
        this.orderDomainService = orderDomainService;
    }

    @Override
    public PageResult<Order> getOrdersByRestaurantIdAndStates(Long restaurantId, States state, int page, int size) {
        return orderPort.getAllByRestaurantIdAndState(restaurantId, state, page, size);
    }

    @Override
    public OrderTraceabilityClient getOrderTraceability(Long restaurantId, String jwt) {
        return orderDomainService.getOrderTraceability(restaurantId, jwt);
    }

}
