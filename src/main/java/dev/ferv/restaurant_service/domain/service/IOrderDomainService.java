package dev.ferv.restaurant_service.domain.service;

import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.States;

public interface IOrderDomainService {

    void signOrder(Long orderId);
    void updateState(Long orderId, States state);
    Order createOrder(Order order);
    void cancelOrder(Long orderId);

    
}
