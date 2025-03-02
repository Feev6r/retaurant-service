package dev.ferv.restaurant_service.domain.model.client;

public class EmployeeTraceClient {

    Long employeeId;
    String productiveAvarageTime;

    public EmployeeTraceClient(Long id, String productiveAvarageTime) {
        this.employeeId = id;
        this.productiveAvarageTime = productiveAvarageTime;
    }
    
    public Long getEmployeeId() {
        return employeeId;
    }
    public String getProductiveAvarageTime() {
        return productiveAvarageTime;
    }

    
}
