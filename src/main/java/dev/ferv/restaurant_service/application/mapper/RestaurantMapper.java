package dev.ferv.restaurant_service.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import dev.ferv.restaurant_service.application.dto.request.RestaurantRequest;
import dev.ferv.restaurant_service.application.dto.response.RestaurantResponse;
import dev.ferv.restaurant_service.domain.model.Restaurant;

@Mapper(componentModel = "spring", 
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    Restaurant toRestaurant(RestaurantRequest restaurantRequest);

    RestaurantResponse toResponse(Restaurant restaurant);
    List<RestaurantResponse> toResponsesList(List<Restaurant> restaurants);

}
