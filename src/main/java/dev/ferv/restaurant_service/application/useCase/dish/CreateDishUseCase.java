package dev.ferv.restaurant_service.application.useCase.dish;


import org.springframework.stereotype.Component;
import dev.ferv.restaurant_service.domain.model.Dish;
import dev.ferv.restaurant_service.domain.port.in.dish.ICreateDishUseCase;
import dev.ferv.restaurant_service.domain.port.out.IDishPort;


@Component
public class CreateDishUseCase implements ICreateDishUseCase{

    private final IDishPort dishPort;

    public CreateDishUseCase(IDishPort dishPort) {
        this.dishPort = dishPort;
    }

    public void CreateDish(Dish dish){
        dishPort.saveDish(dish);
    }

}
