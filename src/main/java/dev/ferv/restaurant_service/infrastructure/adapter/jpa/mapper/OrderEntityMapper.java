package dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper;

import org.mapstruct.Mapper;
import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.order.OrderEntity;

@Mapper(componentModel = "spring", uses = DishEntityMapper.class)
public interface OrderEntityMapper {
    
    Order toOrder(OrderEntity entity);
    OrderEntity toEntity(Order order);
}
