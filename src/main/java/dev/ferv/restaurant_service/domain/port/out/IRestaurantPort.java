package dev.ferv.restaurant_service.domain.port.out;

import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.model.Restaurant;

public interface IRestaurantPort {

    void saveRestaurant(Restaurant restaurant);
    Restaurant getByOwnerId(Long id); 
    PageResult<Restaurant> getAllRestaurant(int page, int size);
}
