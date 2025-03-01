package dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.ferv.restaurant_service.domain.model.States;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.order.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

    Page<OrderEntity> findAllByRestaurantIdAndState(Long restaurantId, States state, Pageable pageable);
    boolean existsByClientIdAndStateIn(Long clientId, List<States> states);

}
