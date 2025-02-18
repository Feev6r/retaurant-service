package dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

    Optional<CategoryEntity> findByName(String name);
} 
