package dev.ferv.restaurant_service.domain.port.in.dish;

import dev.ferv.restaurant_service.domain.model.Dish;

public interface IUpdateDishUseCase {
    void uptdateDish(Dish dish);
}
