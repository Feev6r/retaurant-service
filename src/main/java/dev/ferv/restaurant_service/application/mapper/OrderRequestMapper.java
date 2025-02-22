package dev.ferv.restaurant_service.application.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.application.dto.request.DishOrderRequest;
import dev.ferv.restaurant_service.application.dto.request.OrderRequest;
import dev.ferv.restaurant_service.domain.model.Dish;
import dev.ferv.restaurant_service.domain.model.DishOrder;
import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.port.in.dish.IGetDishesUseCase;
import dev.ferv.restaurant_service.domain.port.in.order.ICreateOrderUseCase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderRequestMapper {


    public Order toOrder(OrderRequest orderRequest,ICreateOrderUseCase createOrderUseCase, IGetDishesUseCase getDishesUseCase ){

        List<DishOrderRequest> dishOrdersRequests = orderRequest.getDishOrders();
        List<DishOrder> dishOrders = new ArrayList<>();

        for (DishOrderRequest dishOrdersRequest : dishOrdersRequests) {
            DishOrder dishOrder = new DishOrder();

            Dish dish = getDishesUseCase.getDish(dishOrdersRequest.getDishId());

            dishOrder.setDish(dish);
            dishOrder.setQuantity(dishOrdersRequest.getQuantity());

            dishOrders.add(dishOrder);
        }

        Order order = new Order();
        order.setRestaurantId(orderRequest.getRestaurantId());
        order.setDishOrders(dishOrders);

        return order;
    }
}
