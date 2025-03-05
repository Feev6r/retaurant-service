package dev.ferv.restaurant_service.application.mapper;

import org.mapstruct.Mapper;

import dev.ferv.restaurant_service.application.dto.response.OrderTraceabilityResponse;
import dev.ferv.restaurant_service.domain.model.client.OrderTraceabilityClient;

@Mapper(componentModel = "spring")
public interface OrderTraceabilityMapper {

    OrderTraceabilityResponse toResponse(OrderTraceabilityClient orderTraceabilityClient);

}
