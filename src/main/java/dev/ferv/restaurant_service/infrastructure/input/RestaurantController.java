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
import dev.ferv.restaurant_service.application.dto.response.EmployeeResponse;
import dev.ferv.restaurant_service.application.dto.response.RestaurantResponse;
import dev.ferv.restaurant_service.application.service.interfaces.IRestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/restaurant")
@Validated
@RequiredArgsConstructor
public class RestaurantController {

    private final IRestaurantService restaurantService;

        
    @Operation(
        description = "obtains all the restaurants, ROLE: CLIENT",
        summary = "obtains all the restaurants",
        responses = {
            @ApiResponse(
                description = "Succes",
                responseCode = "200"
            ),
            @ApiResponse(
                description = "Unauthorized",
                responseCode = "403"
            )
        }
    )
    @GetMapping("/")
    public ResponseEntity<Page<RestaurantResponse>> getRestaurants(
    
    @RequestParam(defaultValue = "0") @Min(0) int page, 
    @RequestParam(defaultValue = "5") @Min(1) int size) {

        return ResponseEntity.ok(restaurantService.getRestaurants(page, size));
    }
    

    @Operation(
        description = "creates a restaurant, ROLE: OWNER",
        summary = "obtains all the restaurants",
        responses = {
            @ApiResponse(
                description = "Succes",
                responseCode = "200"
            ),
            @ApiResponse(
                description = "Unauthorized",
                responseCode = "403"
            )
        }
    )
    @PostMapping("/create")
    public ResponseEntity<Void> createRestaurant(@Valid @RequestBody RestaurantRequest restaurantRequest) {
        restaurantService.createRestaurant(restaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
        description = "sets employees to a restaurant. ROLE: OWNER",
        summary = "sets employees to a restaurant",
        responses = {
            @ApiResponse(
                description = "Succes",
                responseCode = "200"
            ),
            @ApiResponse(
                description = "Unauthorized",
                responseCode = "403"
            )
        }
    )
    @PutMapping("/employee")
    public ResponseEntity<Void> setEmployees(@RequestBody List<Long> ids) {
        restaurantService.setEmployees(ids);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
        description = "obtains an employee ranking according their productivity. ROLE: OWNER",
        summary = "obtains an employee ranking",
        responses = {
            @ApiResponse(
                description = "Succes",
                responseCode = "200"
            ),
            @ApiResponse(
                description = "Unauthorized",
                responseCode = "403"
            )
        }
    )
    @GetMapping("/employee/ranking/{restaurantId}")
    public ResponseEntity<List<EmployeeResponse>> getRanking(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.getEmployeeRanking(restaurantId));
    }
    
    

}
