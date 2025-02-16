package dev.ferv.restaurant_service.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantRequest {

    @NotBlank
    @Size(min = 3, message = "The name must be at least 3 characters long.")
    private String name;
    
    @NotBlank
    private String address;

    @NotBlank
    @Pattern(regexp = "^[0-9]+$", message = "The field 'NIT' must only contain numbers.")
    private String nit;

    @NotBlank
    @Pattern(regexp = "^(?:(?:\\+57|57)?(?:60[1-8]|3[0-9]{2})[0-9]{7})$", 
    message = "Not valid phone number.")
    private String phoneNumber;

    @NotBlank
    private String urlLogo;

}
