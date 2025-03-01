package dev.ferv.restaurant_service.domain.port.in.order;

public interface IUpdateEmployeeTraceUseCase {

    void updateEmployeeTrace(Long orderId, String jwt);

}
