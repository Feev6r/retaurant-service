package dev.ferv.restaurant_service.application.dto.response;

import java.util.List;

import dev.ferv.restaurant_service.domain.model.States;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;
    private Long assignedEmployeeId;
    private Long clientId;
    private States state; 
    private List<DishOrderResponse> dishOrders;
}
