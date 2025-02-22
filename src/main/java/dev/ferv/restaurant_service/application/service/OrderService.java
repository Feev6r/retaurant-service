package dev.ferv.restaurant_service.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.ferv.restaurant_service.application.dto.request.OrderRequest;
import dev.ferv.restaurant_service.application.mapper.OrderMapper;
import dev.ferv.restaurant_service.application.mapper.OrderRequestMapper;
import dev.ferv.restaurant_service.application.service.interfaces.IOrderService;
import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.client.OrderTraceClient;
import dev.ferv.restaurant_service.domain.port.in.dish.IGetDishesUseCase;
import dev.ferv.restaurant_service.domain.port.in.order.ICreateOrderTraceabilityUseCase;
import dev.ferv.restaurant_service.domain.port.in.order.ICreateOrderUseCase;

@Service
public class OrderService implements IOrderService{

    private final ICreateOrderUseCase createOrderUseCase;
    private final IGetDishesUseCase getDishesUseCase;
    private final OrderRequestMapper orderRequestMapper;
    private final ICreateOrderTraceabilityUseCase orderTraceabilityUseCase;
    private final OrderMapper orderMapper;

    public OrderService(ICreateOrderUseCase createOrderUseCase, IGetDishesUseCase getDishesUseCase,
            OrderRequestMapper orderRequestMapper, ICreateOrderTraceabilityUseCase orderTraceabilityUseCase,
            OrderMapper orderMapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.getDishesUseCase = getDishesUseCase;
        this.orderRequestMapper = orderRequestMapper;
        this.orderTraceabilityUseCase = orderTraceabilityUseCase;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public void createOrder(OrderRequest orderRequest) {
        

        Order mappedOrder = orderRequestMapper.toOrder(orderRequest, createOrderUseCase, getDishesUseCase);
        
        Order order = createOrderUseCase.createOrder(mappedOrder);   

        OrderTraceClient orderTraceClient = orderMapper.toOrderTraceClient(order);
        orderTraceabilityUseCase.createOrderTraceability(orderTraceClient); //creates the traceability of the order

    }
 
}
