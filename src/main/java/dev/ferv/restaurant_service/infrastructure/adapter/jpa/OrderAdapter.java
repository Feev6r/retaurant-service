package dev.ferv.restaurant_service.infrastructure.adapter.jpa;

import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.port.out.IOrderPort;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.order.OrderEntity;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.OrderEntityMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.OrderRepository;
import dev.ferv.restaurant_service.infrastructure.exeption.NoDataFoundExeption;


public class OrderAdapter implements IOrderPort{

    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper; 

    public OrderAdapter(OrderRepository orderRepository, OrderEntityMapper orderEntityMapper) {
        this.orderRepository = orderRepository;
        this.orderEntityMapper = orderEntityMapper;
    }

    @Override
    public void createOrder(Order order) {
        orderRepository.save(orderEntityMapper.toEntity(order));
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
