package dev.ferv.restaurant_service.application.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.application.dto.response.EmployeeResponse;
import dev.ferv.restaurant_service.application.dto.response.UserResponse;
import dev.ferv.restaurant_service.domain.model.client.EmployeeTraceClient;
import dev.ferv.restaurant_service.domain.port.out.IUserClientPort;
import dev.ferv.restaurant_service.infrastructure.configuration.security.SecurityContext;

@Component
public class EmployeeResponseMapper {


    private final IUserClientPort userClientPort;
    private final UserResponseMapper userResponseMapper;

    public EmployeeResponseMapper(IUserClientPort userClientPort,
            UserResponseMapper userResponseMapper) {
        this.userClientPort = userClientPort;
        this.userResponseMapper = userResponseMapper;
    }

    EmployeeResponse toResponse(EmployeeTraceClient employeeTraceClient){

        UserResponse userClient = userResponseMapper.toResponse(
            userClientPort.getUserById(employeeTraceClient.getEmployeeId(), "Bearer " + SecurityContext.getToken())
        ); 

        EmployeeResponse employeeResponse = new EmployeeResponse(userClient, employeeTraceClient.getProductiveAvarageTime());
        return employeeResponse;
    }


    public List<EmployeeResponse> toResponseList(List<EmployeeTraceClient> employeeTraceClientList){

        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        for (EmployeeTraceClient employeeTraceClient : employeeTraceClientList) {
            employeeResponseList.add(toResponse(employeeTraceClient)); 
        }

        return employeeResponseList;
    }
}
