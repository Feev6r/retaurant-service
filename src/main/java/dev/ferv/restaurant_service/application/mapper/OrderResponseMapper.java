package dev.ferv.restaurant_service.application.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.application.dto.response.DishOrderResponse;
import dev.ferv.restaurant_service.application.dto.response.OrderResponse;
import dev.ferv.restaurant_service.domain.model.DishOrder;
import dev.ferv.restaurant_service.domain.model.Order;

@Component
public class OrderResponseMapper {

    OrderResponse toResponse(Order order){
        if(order == null){
            return null;
        }
        
        List<DishOrderResponse> dishOrderResponses = new ArrayList<>();

        for (DishOrder dishOrder : order.getDishOrders()) {
            DishOrderResponse dishOrderResponse = new DishOrderResponse(
                dishOrder.getQuantity(), 
                dishOrder.getDish().getId(),
                dishOrder.getDish().getName()
                );

            dishOrderResponses.add(dishOrderResponse);
        }

        OrderResponse orderResponse = new OrderResponse(
            order.getId(),
            order.getAssignedEmployeeId(),
            order.getClientId(),
            order.getState(),
            dishOrderResponses
            );

        return orderResponse;
    }
}
