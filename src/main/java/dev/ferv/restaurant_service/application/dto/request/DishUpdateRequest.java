package dev.ferv.restaurant_service.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishUpdateRequest {

    @NotBlank
    public Long id;
    public Double price;
    public String description;
    public boolean isAvailable;
}
