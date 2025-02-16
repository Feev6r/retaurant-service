package dev.ferv.restaurant_service.infrastructure.input;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.ferv.restaurant_service.application.dto.request.RestaurantRequest;
import dev.ferv.restaurant_service.application.dto.response.RestaurantResponse;
import dev.ferv.restaurant_service.application.service.interfaces.IRestaurantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/restaurant")
@Validated
@RequiredArgsConstructor
public class RestaurantController {

    private final IRestaurantService restaurantService;

    @GetMapping("/")
    public ResponseEntity<Page<RestaurantResponse>> getRestaurants(
    
    @RequestParam(defaultValue = "0") @Min(0) int page, 
    @RequestParam(defaultValue = "5") @Min(1) int size) {

        return ResponseEntity.ok(restaurantService.getRestaurants(page, size));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Void> createRestaurant(@Valid @RequestBody RestaurantRequest restaurantRequest) {
        restaurantService.createRestaurant(restaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/employee")
    public ResponseEntity<Void> setEmployees(@RequestBody List<Long> ids) {
        restaurantService.setEmployees(ids);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    

}
