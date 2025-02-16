package dev.ferv.restaurant_service.domain.port.in.restaurant;

import dev.ferv.restaurant_service.domain.model.Restaurant;

public interface ICreateRestaurantUseCase {
    
    void createRestaurant(Restaurant restaurant);
}
