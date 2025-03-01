package dev.ferv.restaurant_service.application.useCase.order;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.model.States;
import dev.ferv.restaurant_service.domain.port.in.order.IGetOrdersUseCase;
import dev.ferv.restaurant_service.domain.port.out.IOrderPort;

@Component
public class GetOrdersUseCase implements IGetOrdersUseCase{

    private final IOrderPort orderPort;

    public GetOrdersUseCase(IOrderPort orderPort) {
        this.orderPort = orderPort;
    }

    @Override
    public PageResult<Order> getOrdersByRestaurantIdAndStates(Long restaurantId, States state, int page, int size) {
        return orderPort.getAllByRestaurantIdAndState(restaurantId, state, page, size);
    }

}
