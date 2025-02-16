package dev.ferv.restaurant_service.application.useCase.dish;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.Dish;
import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.port.in.dish.IGetDishesUseCase;
import dev.ferv.restaurant_service.domain.port.out.IDishPort;

@Component
public class GetDishesUseCase implements IGetDishesUseCase{

    private final IDishPort dishPort;

    public GetDishesUseCase(IDishPort dishPort) {
        this.dishPort = dishPort;
    }

    @Override
    public PageResult<Dish> getDishes(Long restaurantId, int page, int size){
        return dishPort.getByRestaurantId(restaurantId, page, size);
    }

    public Dish getDish(Long dishId){
        return dishPort.getDishById(dishId);
    }

    @Override
    public PageResult<Dish> getByCategory(String name, int page, int size) {
        return dishPort.getDishByCategory(name, page, size);
    }

}
