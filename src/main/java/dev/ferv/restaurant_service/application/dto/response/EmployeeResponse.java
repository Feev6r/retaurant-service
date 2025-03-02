package dev.ferv.restaurant_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeResponse {
    UserResponse userResponse;
    String productiveAvarageTime;
}
