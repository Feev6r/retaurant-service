package dev.ferv.restaurant_service.domain.exeptions;

public class EmployeeDoesNotExistInTheRestaurant extends RuntimeException {

    public EmployeeDoesNotExistInTheRestaurant(String message){
        super(message);
    }

}
