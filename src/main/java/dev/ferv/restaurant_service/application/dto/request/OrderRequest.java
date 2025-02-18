package dev.ferv.restaurant_service.application.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    @NotBlank
    Long restaurantId;
    @NotBlank
    List<DishOrderRequest> dishOrders;
}
