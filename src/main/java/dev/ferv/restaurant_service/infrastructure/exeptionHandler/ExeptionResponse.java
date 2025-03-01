package dev.ferv.restaurant_service.infrastructure.exeptionHandler;

public enum  ExeptionResponse {
    
    DISH_NOT_FOUND("No dish was found"),
    RESTAURANT_NOT_FOUND("No restaurant was found"),
    ORDER_NOT_FOUND("No order was found"),
    NO_DATA_FOUND("No data was found for the requested petition");

    private String message;

    ExeptionResponse(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
