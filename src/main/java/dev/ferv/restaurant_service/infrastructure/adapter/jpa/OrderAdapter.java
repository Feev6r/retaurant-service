package dev.ferv.restaurant_service.infrastructure.adapter.jpa;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.model.States;
import dev.ferv.restaurant_service.domain.port.out.IOrderPort;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.order.DishOrderEntity;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.order.OrderEntity;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.DishOrderMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.OrderEntityMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.PageMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.DishOrderrRepository;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.OrderRepository;
import dev.ferv.restaurant_service.infrastructure.exeption.NoDataFoundExeption;
import dev.ferv.restaurant_service.infrastructure.exeption.OrderNotFoundExeption;


public class OrderAdapter implements IOrderPort{

    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper; 
    private final DishOrderrRepository dishOrderrRepository;
    private final DishOrderMapper dishOrderMapper;
    private final PageMapper pageMapper;
    

    public OrderAdapter(OrderRepository orderRepository, OrderEntityMapper orderEntityMapper,
            DishOrderrRepository dishOrderrRepository, DishOrderMapper dishOrderMapper, PageMapper pageMapper) {
        this.orderRepository = orderRepository;
        this.orderEntityMapper = orderEntityMapper;
        this.dishOrderrRepository = dishOrderrRepository;
        this.dishOrderMapper = dishOrderMapper;
        this.pageMapper = pageMapper;
    }

    @Override
    public Order createOrder(Order order) {

        List<DishOrderEntity> dishOrderEntities = dishOrderMapper.toEntityList(order.getDishOrders());  
        dishOrderrRepository.saveAll(dishOrderEntities);

        OrderEntity orderEntity = orderEntityMapper.toEntity(order);

        orderEntity.setDishOrders(dishOrderEntities);
        return orderEntityMapper.toOrder(orderRepository.save(orderEntity));
    }

    @Override
    public void updateOrder(Order order) {

        OrderEntity orderEntity = orderRepository.findById(order.getId())
            .orElseThrow(NoDataFoundExeption::new);

        if(order.getState() != null) orderEntity.setState(order.getState());
        if(order.getAssignedEmployeeId() != null) orderEntity.setAssignedEmployeeId(order.getAssignedEmployeeId());
        
        orderRepository.save(orderEntity);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderEntityMapper.toOrder(orderRepository.findById(id)
        .orElseThrow(OrderNotFoundExeption::new));
    }

    @Override
    public PageResult<Order> getAllByRestaurantIdAndState(Long restaurantId, States state, int page, int size) {
        return pageMapper.toPageResultOrder(orderRepository.findAllByRestaurantIdAndState(restaurantId, state, PageRequest.of(page, size))) ;
    }

    @Override
    public boolean hasClientAnOrderUnfinished(Long clientId, List<States> states){
        return orderRepository.existsByClientIdAndStateIn(clientId, states);
    }
 
    @Override 
    public Long getCurrentOrder(Long clientId, Long restaurantId, List<States> states){
        OrderEntity orderEntity = orderRepository.findByRestaurantIdAndClientIdAndStateIn(restaurantId, clientId, states)
            .orElse(null);

        if(orderEntity == null) return null;
        return orderEntity.getId();
    }
}
