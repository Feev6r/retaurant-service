package dev.ferv.restaurant_service.domain.exeptions;

public class StateExeption extends RuntimeException{

    public StateExeption(String message){
        super(message);
    }
}
