package dev.ferv.restaurant_service.domain.model;

import java.util.List;

public class Order {

    private Long id;
    private Long restaurantId; //map only id and name
    private Long assignedEmployeeId;
    private Long clientId;
    private States state; 
    private List<DishOrder> dishOrders;

    public Order(){}

    public Order(Long id, Long restaurantId, Long assignedEmployeeId, Long clientId, States state,
            List<DishOrder> dishes) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.assignedEmployeeId = assignedEmployeeId;
        this.clientId = clientId;
        this.state = state;
        this.dishOrders = dishes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<DishOrder> getDishOrders() {
        return dishOrders;
    }

    public void setDishOrders(List<DishOrder> dishes) {
        this.dishOrders = dishes;
    }

    public Long getAssignedEmployeeId() {
        return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(Long assignedEmployee) {
        this.assignedEmployeeId = assignedEmployee;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
    

}
