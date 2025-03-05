package dev.ferv.restaurant_service.application.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.ferv.restaurant_service.application.dto.request.OrderRequest;
import dev.ferv.restaurant_service.application.dto.response.OrderResponse;
import dev.ferv.restaurant_service.application.dto.response.OrderTraceabilityResponse;
import dev.ferv.restaurant_service.application.mapper.OrderMapper;
import dev.ferv.restaurant_service.application.mapper.OrderRequestMapper;
import dev.ferv.restaurant_service.application.mapper.OrderTraceabilityMapper;
import dev.ferv.restaurant_service.application.mapper.PageDtoMapper;
import dev.ferv.restaurant_service.application.service.interfaces.IOrderService;
import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.model.States;
import dev.ferv.restaurant_service.domain.model.client.OrderTraceClient;
import dev.ferv.restaurant_service.domain.port.in.dish.IGetDishesUseCase;
import dev.ferv.restaurant_service.domain.port.in.order.ICancelOrderUseCase;
import dev.ferv.restaurant_service.domain.port.in.order.ICreateOrderTraceabilityUseCase;
import dev.ferv.restaurant_service.domain.port.in.order.ICreateOrderUseCase;
import dev.ferv.restaurant_service.domain.port.in.order.IGetOrdersUseCase;
import dev.ferv.restaurant_service.domain.port.in.order.ISignOrderUseCase;
import dev.ferv.restaurant_service.domain.port.in.order.IUpdateEmployeeTraceUseCase;
import dev.ferv.restaurant_service.domain.port.in.order.IUpdateOrderStateUseCase;
import dev.ferv.restaurant_service.domain.port.in.order.IUpdateOrderTraceabilityUseCase;
import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import dev.ferv.restaurant_service.infrastructure.configuration.security.SecurityContext;

@Service
public class OrderService implements IOrderService{

    private final ICreateOrderUseCase createOrderUseCase;
    private final IGetDishesUseCase getDishesUseCase;
    private final OrderRequestMapper orderRequestMapper;
    private final ICreateOrderTraceabilityUseCase orderTraceabilityUseCase;
    private final ISignOrderUseCase signOrderUseCase;
    private final IUpdateOrderStateUseCase updateOrderStateUseCase;
    private final IUpdateOrderTraceabilityUseCase updateOrderTraceabilityUseCase;
    private final OrderMapper orderMapper;
    private final IGetOrdersUseCase getOrdersUseCase;
    private final IUpdateEmployeeTraceUseCase updateEmployeeTraceUseCase;
    private final PageDtoMapper pageDtoMapper;
    private final ICancelOrderUseCase cancelOrderUseCase;
    private final IJwtPort jwtPort;
    private final OrderTraceabilityMapper orderTraceabilityMapper;

    public OrderService(ICreateOrderUseCase createOrderUseCase, IGetDishesUseCase getDishesUseCase,
            OrderRequestMapper orderRequestMapper, ICreateOrderTraceabilityUseCase orderTraceabilityUseCase,
            ISignOrderUseCase signOrderUseCase, IUpdateOrderStateUseCase updateOrderStateUseCase,
            IUpdateOrderTraceabilityUseCase updateOrderTraceabilityUseCase, OrderMapper orderMapper,
            IGetOrdersUseCase getOrdersUseCase, IUpdateEmployeeTraceUseCase updateEmployeeTraceUseCase,
            PageDtoMapper pageDtoMapper, ICancelOrderUseCase cancelOrderUseCase, IJwtPort jwtPort,
            OrderTraceabilityMapper orderTraceabilityMapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.getDishesUseCase = getDishesUseCase;
        this.orderRequestMapper = orderRequestMapper;
        this.orderTraceabilityUseCase = orderTraceabilityUseCase;
        this.signOrderUseCase = signOrderUseCase;
        this.updateOrderStateUseCase = updateOrderStateUseCase;
        this.updateOrderTraceabilityUseCase = updateOrderTraceabilityUseCase;
        this.orderMapper = orderMapper;
        this.getOrdersUseCase = getOrdersUseCase;
        this.updateEmployeeTraceUseCase = updateEmployeeTraceUseCase;
        this.pageDtoMapper = pageDtoMapper;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.jwtPort = jwtPort;
        this.orderTraceabilityMapper = orderTraceabilityMapper;
    }

    @Override
    @Transactional
    public void createOrder(OrderRequest orderRequest) {
        
        Order mappedOrder = orderRequestMapper.toOrder(orderRequest, createOrderUseCase, getDishesUseCase);
        
        Order order = createOrderUseCase.createOrder(mappedOrder);   

        OrderTraceClient orderTraceClient = orderMapper.toOrderTraceClient(order);
        orderTraceabilityUseCase.createOrderTraceability(orderTraceClient, "Bearer " + SecurityContext.getToken()); //creates the traceability of the order

    }

    @Override
    @Transactional
    public void signOrder(Long orderId) {
        signOrderUseCase.signOrder(orderId);
        updateOrderState(orderId, States.PREPARING);
        updateEmployeeTraceUseCase.updateEmployeeTrace(orderId, "Bearer " + SecurityContext.getToken());
    }
    

    @Override
    @Transactional
    public void updateOrderState(Long orderId, States state) {
        updateOrderStateUseCase.updateOrderState(orderId, state);     
        updateOrderTraceabilityUseCase.updateOrderTrace(orderId, jwtPort.getIdBySecurityContext(), state, "Bearer " + SecurityContext.getToken());

    }

    @Override
    @Transactional
    public void deliverOrder(Long orderId, String pin){
        updateOrderStateUseCase.deliverOrder(orderId, pin);
        updateOrderTraceabilityUseCase.updateOrderTrace(orderId, jwtPort.getIdBySecurityContext(), States.DELIVERED, "Bearer " + SecurityContext.getToken());
    }

    @Override
    public void cancelOrder(Long orderId) {
        cancelOrderUseCase.cancelOrder(orderId);
        updateOrderTraceabilityUseCase.updateOrderTrace(orderId, jwtPort.getIdBySecurityContext(), States.CANCELLED, "Bearer " + SecurityContext.getToken());
    }

    @Override
    public Page<OrderResponse> getOrdersByRestaurantIdAndState(Long restaurantId, States state, int page, int size){
        PageResult<Order> pageResult = getOrdersUseCase.getOrdersByRestaurantIdAndStates(restaurantId, state, page, size);
        return pageDtoMapper.toPageOrderResponse(pageResult);
    }
 
    @Override
    public OrderTraceabilityResponse getOrderTraceability(Long restaurantId){
        return orderTraceabilityMapper.toResponse(
            getOrdersUseCase.getOrderTraceability(restaurantId, "Bearer " + SecurityContext.getToken())
        );
    }
}
