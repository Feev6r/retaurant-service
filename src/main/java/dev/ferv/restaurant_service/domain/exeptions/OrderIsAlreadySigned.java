package dev.ferv.restaurant_service.domain.exeptions;

public class OrderIsAlreadySigned extends RuntimeException{

    public OrderIsAlreadySigned(String message){
        super(message);
    }

}
