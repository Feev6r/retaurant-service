package dev.ferv.restaurant_service.domain.port.in.dish;

import dev.ferv.restaurant_service.domain.model.Dish;

public interface ICreateDishUseCase {

    void CreateDish(Dish dish);
}
