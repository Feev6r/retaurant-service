package dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.ferv.restaurant_service.domain.model.Dish;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.DishEntity;

@Mapper(componentModel = "spring")
public interface DishEntityMapper {

    @Mapping(source = "restaurant.name", target = "restaurantName")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    Dish toDish(DishEntity dishEntity); 

    @Mapping(target = "restaurant", ignore = true) 
    DishEntity toEntity(Dish dish);
    
    List<Dish> toDishesList(List<DishEntity> dishEntities);

    
}
