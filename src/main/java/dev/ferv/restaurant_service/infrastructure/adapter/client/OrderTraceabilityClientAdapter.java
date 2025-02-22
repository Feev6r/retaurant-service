package dev.ferv.restaurant_service.infrastructure.adapter.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import dev.ferv.restaurant_service.domain.model.client.OrderTraceClient;
import dev.ferv.restaurant_service.domain.port.out.IOrderTraceabilityPort;

@FeignClient(name = "traceability-service")
public interface OrderTraceabilityClientAdapter extends IOrderTraceabilityPort{

    @PostMapping("/orderTrace/create")
    void createOrderTrace(@RequestBody OrderTraceClient orderTraceClient, @RequestHeader("Authorization") String jwt);

}
