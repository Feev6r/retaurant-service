package dev.ferv.restaurant_service.application.mapper;

import org.mapstruct.Mapper;
import dev.ferv.restaurant_service.application.dto.response.OrderResponse;
import dev.ferv.restaurant_service.domain.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toResponse(Order order);
}
