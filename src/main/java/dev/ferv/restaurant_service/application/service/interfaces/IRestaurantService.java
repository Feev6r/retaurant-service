package dev.ferv.restaurant_service.application.service.interfaces;


import java.util.List;

import org.springframework.data.domain.Page;

import dev.ferv.restaurant_service.application.dto.request.RestaurantRequest;
import dev.ferv.restaurant_service.application.dto.response.RestaurantResponse;

public interface IRestaurantService {

    void createRestaurant(RestaurantRequest restaurantRequest); 
    Page<RestaurantResponse> getRestaurants(int page, int size);
    void setEmployees(List<Long> ids);

}
