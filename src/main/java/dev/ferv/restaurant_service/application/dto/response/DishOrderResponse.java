package dev.ferv.restaurant_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DishOrderResponse {

    private Integer quantity;
    private Long dishId;
    private String dishName;

}
