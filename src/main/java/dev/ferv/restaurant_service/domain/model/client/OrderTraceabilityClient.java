package dev.ferv.restaurant_service.domain.model.client;

import java.util.List;

public class OrderTraceabilityClient {

    private Long orderId;
    private List<StateLog> states;
    private String duration;

    public OrderTraceabilityClient(Long orderId, List<StateLog> states, String duration) {
        this.orderId = orderId;
        this.states = states;
        this.duration = duration;
    } 

    public Long getOrderId() {
        return orderId;
    }
    public List<StateLog> getStates() {
        return states;
    }
    public String getDuration() {
        return duration;
    }

}
