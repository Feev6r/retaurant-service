package dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper;

import java.util.List;
import org.mapstruct.Mapper;

import dev.ferv.restaurant_service.domain.model.Restaurant;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.RestaurantEntity;

@Mapper(componentModel = "spring", uses = DishEntityMapper.class)
public interface RestaurantEntityMapper {


    Restaurant toRestaurant(RestaurantEntity restaurantEntity);
    RestaurantEntity toEntity(Restaurant restaurant);
    List<Restaurant> toList(List<RestaurantEntity>  restaurantEntities);
}
