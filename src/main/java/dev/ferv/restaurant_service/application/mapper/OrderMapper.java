package dev.ferv.restaurant_service.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.client.OrderTraceClient;

@Mapper(componentModel = "spring", 
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    
    @Mapping(source = "id", target = "orderId")
    OrderTraceClient toOrderTraceClient(Order order);
}
