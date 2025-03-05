package dev.ferv.restaurant_service.domain.model.client;

import dev.ferv.restaurant_service.domain.model.States;

public class StateLog {

    private States state;
    private String completedDate;
    
    public StateLog(States state, String completedDate) {
        this.state = state;
        this.completedDate = completedDate;
    }

    public States getState() {
        return state;
    }

    public String getCompletedDate() {
        return completedDate;
    }

}
