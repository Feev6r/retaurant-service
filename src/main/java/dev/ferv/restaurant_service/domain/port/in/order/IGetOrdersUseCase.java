package dev.ferv.restaurant_service.domain.port.in.order;


import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.model.States;

public interface IGetOrdersUseCase {

    PageResult<Order> getOrdersByRestaurantIdAndStates(Long restaurantId, States state, int page, int size);
}
