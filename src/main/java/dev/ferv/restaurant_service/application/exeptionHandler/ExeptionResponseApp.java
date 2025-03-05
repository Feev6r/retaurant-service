package dev.ferv.restaurant_service.application.exeptionHandler;

public enum ExeptionResponseApp {

    EMPLOYEE_DOES_NOT_EXIST_IN_THE_RESTAURANT("Employee not found in the current restaurant"), 
    ORDER_ALREADY_SIGNED("Order already signed"),
    UNFINISHED_ORDER("Only one order at time"),
    NOT_CURRENT_ORDER_FOUND("The user does not have an active order"),
    RESTAURANT_NOT_BELONGS("The restaurant does not belongs to the current owner"),
    NOT_VALID_PIN("The security pin was not valid"),
    STATE_INVALID_UPDATE("Invalid state update");

    
    private String message;

    ExeptionResponseApp(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
