package dev.ferv.restaurant_service.application.useCase.restaurant;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.Restaurant;
import dev.ferv.restaurant_service.domain.port.in.restaurant.ISetEmployeesUseCase;
import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import dev.ferv.restaurant_service.domain.port.out.IRestaurantPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SetEmployeesUseCase implements ISetEmployeesUseCase{

    private final IRestaurantPort restaurantPort;
    private final IJwtPort jwtPort;

    @Override
    public void setEmployee(List<Long> ids) {

        Restaurant restaurant = restaurantPort.getByOwnerId(jwtPort.getIdBySecurityContext());

        for (Long employeeId : ids) {
            restaurant.getEmployeesId().add(employeeId);
        }

        restaurantPort.saveRestaurant(restaurant);
    }


}
