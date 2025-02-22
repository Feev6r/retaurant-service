package dev.ferv.restaurant_service.domain.port.out;

import dev.ferv.restaurant_service.domain.model.Order;

public interface IOrderPort {

    Order createOrder(Order order);
    void updateOrder(Order order);
}
