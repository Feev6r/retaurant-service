package dev.ferv.restaurant_service.application.service.interfaces;

import dev.ferv.restaurant_service.application.dto.request.OrderRequest;

public interface IOrderService {

    void createOrder(OrderRequest orderRequest);
}
