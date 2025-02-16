package dev.ferv.restaurant_service.domain.port.in.restaurant;

import java.util.List;

public interface ISetEmployeesUseCase {

    void setEmployee(List<Long> ids);
}
