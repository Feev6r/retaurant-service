package dev.ferv.restaurant_service.application.useCase.restaurant;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.model.Restaurant;
import dev.ferv.restaurant_service.domain.port.in.restaurant.IGetRestaurantUseCase;
import dev.ferv.restaurant_service.domain.port.out.IRestaurantPort;

@Component
public class GetRestaurantsUseCase implements IGetRestaurantUseCase{

    private final IRestaurantPort restaurantPort;

    public GetRestaurantsUseCase(IRestaurantPort restaurantPort) {
        this.restaurantPort = restaurantPort;
    }

    @Override
    public PageResult<Restaurant> getRestaurants(int page, int size){
        return restaurantPort.getAllRestaurant(page, size);
    }
}
