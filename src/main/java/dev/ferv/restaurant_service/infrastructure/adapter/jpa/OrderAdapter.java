package dev.ferv.restaurant_service.infrastructure.adapter.jpa;

import java.util.List;
import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.port.out.IOrderPort;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.order.DishOrderEntity;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.order.OrderEntity;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.DishOrderMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.OrderEntityMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.DishOrderrRepository;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.OrderRepository;
import dev.ferv.restaurant_service.infrastructure.exeption.NoDataFoundExeption;


public class OrderAdapter implements IOrderPort{

    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper; 
    private final DishOrderrRepository dishOrderrRepository;
    private final DishOrderMapper dishOrderMapper;
    

    
    public OrderAdapter(OrderRepository orderRepository, OrderEntityMapper orderEntityMapper,
            DishOrderrRepository dishOrderrRepository, DishOrderMapper dishOrderMapper) {
        this.orderRepository = orderRepository;
        this.orderEntityMapper = orderEntityMapper;
        this.dishOrderrRepository = dishOrderrRepository;
        this.dishOrderMapper = dishOrderMapper;
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
        if(order.getAssignedEmployeeId() != null)orderEntity.setAssignedEmployeeId(order.getAssignedEmployeeId());
        
        orderRepository.save(orderEntity);
    }
 
}
