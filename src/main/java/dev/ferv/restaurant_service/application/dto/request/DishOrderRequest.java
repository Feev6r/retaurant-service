package dev.ferv.restaurant_service.application.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishOrderRequest {

    @Size(min = 1, message =  "The quantity must be almost 1 per dish order")
    private Integer quantity;
    @NotNull
    private Long dishId;

}
