package dev.ferv.restaurant_service.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishRequest {

    @NotBlank
    @Size(min = 3, message = "The name must be at least 3 characters long.")
    private String name;

    @NotNull
    private Double price;

    @Size(min = 50, message = "The description is too short.")
    private String description;
    @NotBlank
    private String urlImage;


    @Size(min = 3, message = "The description is too short.")
    @NotBlank
    private String category;

}
