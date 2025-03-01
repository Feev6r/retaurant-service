package dev.ferv.restaurant_service.application.useCase.order;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.port.in.order.IUpdateEmployeeTraceUseCase;
import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import dev.ferv.restaurant_service.domain.port.out.ITraceabilityPort;

@Component
public class UpdateEmployeeTraceUseCase implements IUpdateEmployeeTraceUseCase {

    private final ITraceabilityPort traceabilityPort;
    private final IJwtPort jwtPort;


    public UpdateEmployeeTraceUseCase(ITraceabilityPort traceabilityPort, IJwtPort jwtPort) {
        this.traceabilityPort = traceabilityPort;
        this.jwtPort = jwtPort;
    }

    @Override
    public void updateEmployeeTrace(Long orderId, String jwt) {
        traceabilityPort.updateEmployeTrace(orderId, jwtPort.getIdBySecurityContext(), jwt);
    }

}
