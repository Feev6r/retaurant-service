package dev.ferv.restaurant_service.domain.port.in.order;

import dev.ferv.restaurant_service.domain.model.States;

public interface IUpdateOrderStateUseCase {

    void updateOrderState(Long orderId, States state);
    void deliverOrder(Long orderId, String pin);
}
