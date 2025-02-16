package dev.ferv.restaurant_service.application.useCase.dish;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.Dish;
import dev.ferv.restaurant_service.domain.port.in.dish.IUpdateDishUseCase;
import dev.ferv.restaurant_service.domain.port.out.IDishPort;

@Component
public class UpdateDishUseCase implements IUpdateDishUseCase {

    private final IDishPort dishPort;

    public UpdateDishUseCase(IDishPort dishPort) {
        this.dishPort = dishPort;
    }

    public void uptdateDish(Dish dish){
        dishPort.updateDish(dish);
    }

}
