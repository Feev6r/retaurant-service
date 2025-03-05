package dev.ferv.restaurant_service.domain.service;

import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.States;
import dev.ferv.restaurant_service.domain.model.client.OrderTraceabilityClient;

public interface IOrderDomainService {

    void signOrder(Long orderId);
    void updateState(Long orderId, States state);
    Order createOrder(Order order);
    void cancelOrder(Long orderId);
    OrderTraceabilityClient getOrderTraceability(Long restaurantId, String jwt);
    void setOrderAsDelivered(Long orderId, String code);
}
