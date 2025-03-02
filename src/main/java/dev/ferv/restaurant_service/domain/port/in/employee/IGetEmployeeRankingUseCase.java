package dev.ferv.restaurant_service.domain.port.in.employee;

import java.util.List;

import dev.ferv.restaurant_service.domain.model.client.EmployeeTraceClient;

public interface IGetEmployeeRankingUseCase {

    List<EmployeeTraceClient> getEmployeeRanking(List<Long> employeeIds, String jwt);
}
