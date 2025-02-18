package dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.DishEntity;

public interface DishRepository extends JpaRepository<DishEntity, Long> {

    Page<DishEntity> findByRestaurantIdAndIsAvailable(Long id, Pageable pageable, boolean isAvailable);
    Page<DishEntity> findByCategoryNameAndIsAvailable(String name, Pageable pageable, boolean isAvailable);

    default Page<DishEntity> findByRestaurantId(Long id, Pageable pageable){
        return findByRestaurantIdAndIsAvailable(id, pageable, true);
    }

    default Page<DishEntity> findByCategoryName(String name, Pageable pageable){
        return findByCategoryNameAndIsAvailable(name, pageable, true);
    }
}
