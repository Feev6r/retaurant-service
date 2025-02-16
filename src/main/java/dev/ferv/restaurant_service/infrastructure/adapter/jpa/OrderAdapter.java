package dev.ferv.restaurant_service.infrastructure.adapter.jpa;

import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.OrderRepository;

public class OrderAdapter {

    private final OrderRepository orderRepository;

    public OrderAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    


}
