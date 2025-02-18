package dev.ferv.restaurant_service.domain.model;

public class DishOrder {

    private Long id;
    private Integer quantity;
    private Dish dish;

    public DishOrder(){}

    public DishOrder(Long id, Integer quantity, Dish dish) {
        this.id = id;
        this.quantity = quantity;
        this.dish = dish;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Dish getDish() {
        return dish;
    }
    public void setDish(Dish dish) {
        this.dish = dish;
    }

    
    
}
