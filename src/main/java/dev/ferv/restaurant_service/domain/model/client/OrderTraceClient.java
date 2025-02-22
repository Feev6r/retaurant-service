package dev.ferv.restaurant_service.domain.model.client;

import dev.ferv.restaurant_service.domain.model.States;

public class OrderTraceClient {

    Long clientId;
    Long orderId;
    States state;

    public OrderTraceClient(Long clientId, Long orderId, States state) {
        this.clientId = clientId;
        this.orderId = orderId;
        this.state = state;
    }
    public Long getClientId() {
        return clientId;
    }
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public States getState() {
        return state;
    }
    public void setState(States state) {
        this.state = state;
    }

}
