package dev.ferv.restaurant_service.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.ferv.restaurant_service.application.dto.response.OrderResponse;
import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.client.OrderTraceClient;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse toResponse(Order order);
    
    @Mapping(source = "id", target = "orderId")
    OrderTraceClient toOrderTraceClient(Order order);
}
