package dev.ferv.restaurant_service.domain.port.out;


import dev.ferv.restaurant_service.domain.model.Dish;
import dev.ferv.restaurant_service.domain.model.PageResult;

public interface IDishPort {

    void saveDish(Dish dish);
    void updateDish(Dish dish);
    PageResult<Dish> getByRestaurantId(Long restaurantId, int page, int size);
    Dish getDishById(Long id);
    PageResult<Dish> getDishByCategory(String name, int page, int size);

}
