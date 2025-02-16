package dev.ferv.restaurant_service.domain.model;

public class Dish {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private String urlImage;
    private boolean isAvailable = true;
    private String restaurantName;
    private Long restaurantId;
    private Category category;
    
    public Dish(){}


    public Dish(Long id, String name, Double price, String description, String urlImage, boolean isAvailable,
            String restaurantName, Long restaurantId, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.urlImage = urlImage;
        this.isAvailable = isAvailable;
        this.restaurantName = restaurantName;
        this.restaurantId = restaurantId;
        this.category = category;
    }
    
    public Long getId() {
        return id;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public Long getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }


}
