package dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.order.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

}
