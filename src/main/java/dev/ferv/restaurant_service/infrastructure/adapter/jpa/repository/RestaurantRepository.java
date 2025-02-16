package dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.RestaurantEntity;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    Optional<RestaurantEntity> findByOwnerId(Long userId);
}
