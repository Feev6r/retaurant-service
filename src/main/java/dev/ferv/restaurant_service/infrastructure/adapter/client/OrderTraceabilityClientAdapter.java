package dev.ferv.restaurant_service.infrastructure.adapter.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "treaceability-service")
public class OrderTraceabilityClientAdapter {

}
