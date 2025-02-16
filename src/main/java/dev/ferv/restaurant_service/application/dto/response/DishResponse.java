package dev.ferv.restaurant_service.application.dto.response;


import dev.ferv.restaurant_service.domain.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DishResponse {

    private String restaurantName;
    private String name;
    private Double price;
    private String description;
    private String urlImage;
    private Category category;
}
