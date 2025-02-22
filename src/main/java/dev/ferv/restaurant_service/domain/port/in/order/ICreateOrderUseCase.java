package dev.ferv.restaurant_service.domain.port.in.order;

import dev.ferv.restaurant_service.domain.model.Order;

public interface ICreateOrderUseCase {

    Order createOrder(Order order);
}
