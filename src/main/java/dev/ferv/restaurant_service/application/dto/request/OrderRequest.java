package dev.ferv.restaurant_service.application.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    @NotNull
    @Positive
    Long restaurantId;

    @NotNull
    @NotEmpty
    List<DishOrderRequest> dishOrders;
}
