package dev.ferv.restaurant_service.domain.model;

import java.util.List;

public class Order {

    private Long id;
    private Long restaurantId; //map only id and name
    private List<DishOrder> dishes;

    public Order(Long id, Long restaurantId, List<DishOrder> dishes) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.dishes = dishes;
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
    public List<DishOrder> getDishes() {
        return dishes;
    }
    public void setDishes(List<DishOrder> dishes) {
        this.dishes = dishes;
    }

    
}
