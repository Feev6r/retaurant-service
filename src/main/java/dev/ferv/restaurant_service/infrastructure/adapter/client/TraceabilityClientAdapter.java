package dev.ferv.restaurant_service.infrastructure.adapter.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import dev.ferv.restaurant_service.domain.model.States;
import dev.ferv.restaurant_service.domain.model.client.EmployeeTraceClient;
import dev.ferv.restaurant_service.domain.model.client.OrderTraceClient;
import dev.ferv.restaurant_service.domain.model.client.OrderTraceabilityClient;
import dev.ferv.restaurant_service.domain.port.out.ITraceabilityPort;

@FeignClient(name = "traceability-service")
public interface TraceabilityClientAdapter extends ITraceabilityPort{

    @PostMapping("/orderTrace/create")
    void createOrderTrace(@RequestBody OrderTraceClient orderTraceClient, @RequestHeader("Authorization") String jwt);

    @PutMapping("/orderTrace/update/{orderId}/{employeeId}/{newState}")
    void updateOrderTrace(@PathVariable Long orderId,  @PathVariable Long employeeId, @PathVariable States newState, @RequestHeader("Authorization") String jwt);

    @PutMapping("/employeeTrace/sign/{orderId}/{employeeId}")
    void updateEmployeTrace(@PathVariable Long orderId, @PathVariable Long employeeId, @RequestHeader("Authorization") String jwt);

    @PostMapping("/employeeTrace/getRanking")
    List<EmployeeTraceClient> getEmployeeRanking(@RequestBody List<Long> employeeIds,  @RequestHeader("Authorization") String jwt);

    @GetMapping("/orderTrace/{orderId}")
    OrderTraceabilityClient getOrderTraceability(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt);
    

}
