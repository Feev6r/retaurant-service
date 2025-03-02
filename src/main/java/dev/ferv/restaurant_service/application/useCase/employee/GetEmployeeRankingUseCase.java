package dev.ferv.restaurant_service.application.useCase.employee;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.client.EmployeeTraceClient;
import dev.ferv.restaurant_service.domain.port.in.employee.IGetEmployeeRankingUseCase;
import dev.ferv.restaurant_service.domain.port.out.ITraceabilityPort;

@Component
public class GetEmployeeRankingUseCase implements IGetEmployeeRankingUseCase{

    private final ITraceabilityPort traceabilityPort;
        
    public GetEmployeeRankingUseCase(ITraceabilityPort traceabilityPort) {
        this.traceabilityPort = traceabilityPort;
    }


    @Override
    public List<EmployeeTraceClient> getEmployeeRanking(List<Long> employeeIds, String jwt) {
        return traceabilityPort.getEmployeeRanking(employeeIds, jwt);
    }

}
