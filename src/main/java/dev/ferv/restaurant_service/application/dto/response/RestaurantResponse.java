package dev.ferv.restaurant_service.application.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantResponse {

    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String urlLogo;
    private List<DishResponse> dishes;

}
