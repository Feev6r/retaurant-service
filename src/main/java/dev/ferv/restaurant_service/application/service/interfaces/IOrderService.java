package dev.ferv.restaurant_service.application.service.interfaces;

import org.springframework.data.domain.Page;

import dev.ferv.restaurant_service.application.dto.request.OrderRequest;
import dev.ferv.restaurant_service.application.dto.response.OrderResponse;
import dev.ferv.restaurant_service.application.dto.response.OrderTraceabilityResponse;
import dev.ferv.restaurant_service.domain.model.States;

public interface IOrderService {

    void createOrder(OrderRequest orderRequest);
    void signOrder(Long orderId);
    void updateOrderState(Long orderId, States state);
    void cancelOrder(Long orderId);
    Page<OrderResponse> getOrdersByRestaurantIdAndState(Long restaurantId, States state, int page, int size);
    OrderTraceabilityResponse getOrderTraceability(Long restaurantId);
    void deliverOrder(Long orderId, String pin);
}
