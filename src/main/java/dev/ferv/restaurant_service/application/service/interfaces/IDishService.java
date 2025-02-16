package dev.ferv.restaurant_service.application.service.interfaces;

import org.springframework.data.domain.Page;

import dev.ferv.restaurant_service.application.dto.request.DishRequest;
import dev.ferv.restaurant_service.application.dto.request.DishUpdateRequest;
import dev.ferv.restaurant_service.application.dto.response.DishResponse;

public interface IDishService {

    void createDish(DishRequest dishRequest);
    Page<DishResponse> getDishes(Long restaurantId, int page, int size);
    void updateDish(DishUpdateRequest DishUpdateRequest);
    Page<DishResponse> getDishesByCategory(String category, int page, int size); 

}
