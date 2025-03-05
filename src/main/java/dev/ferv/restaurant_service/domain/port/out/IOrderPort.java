package dev.ferv.restaurant_service.domain.port.out;

import java.util.List;

import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.model.States;

public interface IOrderPort {

    Order createOrder(Order order);
    void updateOrder(Order order);
    Order getOrderById(Long id);
    PageResult<Order> getAllByRestaurantIdAndState(Long restaurantId, States state, int page, int size);
    boolean hasClientAnOrderUnfinished(Long clientId, List<States> states);
    Long getCurrentOrder(Long clientId, Long restaurantId, List<States> states);
}
