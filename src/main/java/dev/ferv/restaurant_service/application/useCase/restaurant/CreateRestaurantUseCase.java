package dev.ferv.restaurant_service.application.useCase.restaurant;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.Restaurant;
import dev.ferv.restaurant_service.domain.port.in.restaurant.ICreateRestaurantUseCase;
import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import dev.ferv.restaurant_service.domain.port.out.IRestaurantPort;

@Component
public class CreateRestaurantUseCase implements ICreateRestaurantUseCase{

    private final IRestaurantPort restaurantPort;
    private final IJwtPort jwtPort;

    public CreateRestaurantUseCase(IRestaurantPort restaurantPort, IJwtPort jwtPort) {
        this.restaurantPort = restaurantPort;
        this.jwtPort = jwtPort; 
    }

    public void createRestaurant(Restaurant restaurant){
        restaurant.setOwnerId(jwtPort.getIdBySecurityContext()); //add owner's id to the restaurant
        restaurantPort.saveRestaurant(restaurant);
    }

}
