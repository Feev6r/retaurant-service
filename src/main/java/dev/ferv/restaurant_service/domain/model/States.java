package dev.ferv.restaurant_service.domain.model;

public enum States {
    CANCELLED(1),
    PENDING(1),
    PREPARING(2),
    READY(3),
    DELIVERED(4);

    private final int value;

    private States(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
    
}
