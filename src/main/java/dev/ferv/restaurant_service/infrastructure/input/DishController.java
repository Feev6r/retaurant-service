package dev.ferv.restaurant_service.infrastructure.input;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.ferv.restaurant_service.application.dto.request.DishRequest;
import dev.ferv.restaurant_service.application.dto.request.DishUpdateRequest;
import dev.ferv.restaurant_service.application.dto.response.DishResponse;
import dev.ferv.restaurant_service.application.service.interfaces.IDishService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/dish")
@Validated
@RequiredArgsConstructor
public class DishController {

    private final IDishService dishService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Page<DishResponse>> getDishes(

    @PathVariable Long restaurantId, 
    @RequestParam(defaultValue = "0") @Min(0) int page, 
    @RequestParam(defaultValue = "5") @Min(1) int size) {
        
        return ResponseEntity.ok(dishService.getDishes(restaurantId, page, size));
    }

    @GetMapping("category/{category}")
    public ResponseEntity<Page<DishResponse>> getMethodName(

    @PathVariable String category, 
    @RequestParam(defaultValue = "0") @Min(0) int page, 
    @RequestParam(defaultValue = "5") @Min(1) int size) {

        return ResponseEntity.ok(dishService.getDishesByCategory(category, page, size));
    }
    
    
    @PostMapping("/create")
    public ResponseEntity<Void> createDish(@RequestBody @Valid DishRequest dishRequest) {
        dishService.createDish(dishRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build(); 
    }
    
    @PutMapping("/update")
    public ResponseEntity<Void> updateDish(@RequestBody DishUpdateRequest dishUpdateRequest) {
        dishService.updateDish(dishUpdateRequest);
        return ResponseEntity.noContent().build();
    }

}
