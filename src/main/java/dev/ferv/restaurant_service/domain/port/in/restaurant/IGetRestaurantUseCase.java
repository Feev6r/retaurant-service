package dev.ferv.restaurant_service.domain.port.in.restaurant;

import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.model.Restaurant;

public interface IGetRestaurantUseCase {

    PageResult<Restaurant> getRestaurants(int page, int size);
    Restaurant getRestaurantById(Long restaurantId);
}
