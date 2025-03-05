package dev.ferv.restaurant_service.domain.port.out;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import dev.ferv.restaurant_service.domain.model.States;
import dev.ferv.restaurant_service.domain.model.client.EmployeeTraceClient;
import dev.ferv.restaurant_service.domain.model.client.OrderTraceClient;
import dev.ferv.restaurant_service.domain.model.client.OrderTraceabilityClient;

public interface ITraceabilityPort {

    void createOrderTrace(OrderTraceClient orderTraceClient, String jwt);
    void updateOrderTrace(Long orderId,  Long employeeId, States newState, String jwt);
    void updateEmployeTrace(Long orderId, Long employeeId, String jwt);
    List<EmployeeTraceClient> getEmployeeRanking(List<Long> employeeIds, String jwt);
    OrderTraceabilityClient getOrderTraceability(@PathVariable Long orderId, String jwt);

}
