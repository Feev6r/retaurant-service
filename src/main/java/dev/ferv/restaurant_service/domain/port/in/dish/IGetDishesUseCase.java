package dev.ferv.restaurant_service.domain.port.in.dish;


import dev.ferv.restaurant_service.domain.model.Dish;
import dev.ferv.restaurant_service.domain.model.PageResult;

public interface IGetDishesUseCase {

    PageResult<Dish> getDishes(Long restaurantId, int page, int size);
    Dish getDish(Long dishId);
    PageResult<Dish> getByCategory(String name, int page, int size);
}
