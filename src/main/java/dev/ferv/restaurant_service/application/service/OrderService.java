package dev.ferv.restaurant_service.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.ferv.restaurant_service.application.dto.request.OrderRequest;
import dev.ferv.restaurant_service.application.mapper.OrderRequestMapper;
import dev.ferv.restaurant_service.application.service.interfaces.IOrderService;
import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.port.in.dish.IGetDishesUseCase;
import dev.ferv.restaurant_service.domain.port.in.order.ICreateOrderUseCase;

@Service
public class OrderService implements IOrderService{

    private final ICreateOrderUseCase createOrderUseCase;
    private final IGetDishesUseCase getDishesUseCase;
    private final OrderRequestMapper orderRequestMapper;

    public OrderService(ICreateOrderUseCase createOrderUseCase, IGetDishesUseCase getDishesUseCase,
            OrderRequestMapper orderRequestMapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.getDishesUseCase = getDishesUseCase;
        this.orderRequestMapper = orderRequestMapper;
    }

    @Override
    @Transactional
    public void createOrder(OrderRequest orderRequest) {
        
        Order order = orderRequestMapper.toOrder(orderRequest, createOrderUseCase, getDishesUseCase);
        createOrderUseCase.createOrder(order);
    }
 
}
