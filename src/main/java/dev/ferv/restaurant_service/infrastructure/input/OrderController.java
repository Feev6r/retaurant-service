package dev.ferv.restaurant_service.infrastructure.input;

import org.springframework.web.bind.annotation.RestController;

import dev.ferv.restaurant_service.application.dto.request.OrderRequest;
import dev.ferv.restaurant_service.application.dto.response.OrderResponse;
import dev.ferv.restaurant_service.application.dto.response.OrderTraceabilityResponse;
import dev.ferv.restaurant_service.application.service.interfaces.IOrderService;
import dev.ferv.restaurant_service.domain.model.States;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("order")
@Validated
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @Operation(
        description = "make an order. ROLE: CLIENT",
        summary = "make an order",
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
    public ResponseEntity<Void> makeOrder(@Valid @RequestBody OrderRequest request) {
        orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build(); 
    }

    @Operation(
        description = "gets an order by the restaurant and state of it. ROLE: EMPLOYEE",
        summary = "gets an order by parameters",
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
    @GetMapping("/getByStateAndRestaurant/{state}/{restaurantId}")
    public ResponseEntity<Page<OrderResponse>> getByStateAndRestaurant(
    
    @PathVariable States state, 
    @PathVariable Long restaurantId, 
    @RequestParam(defaultValue = "0") @Min(0) int page, 
    @RequestParam(defaultValue = "5") @Min(1) int size) {
        
        return ResponseEntity.ok(orderService.getOrdersByRestaurantIdAndState(restaurantId, state, page, size));
    }
    

    @Operation(
        description = "sign a specific order to continue with process. ROLE: EMPLOYEE",
        summary = "sign order",
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
    @PutMapping("sign/{orderId}")
    public ResponseEntity<Void> signOrder(@PathVariable Long orderId) {
        orderService.signOrder(orderId);
        return ResponseEntity.noContent().build(); 
    }

    @Operation(
        description = "changes the state of an order. ROLE: EMPLOYEE",
        summary = "change order state",
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
    @PutMapping("update/{orderId}/{state}")
    public ResponseEntity<Void> updateOrder(@PathVariable Long orderId, @PathVariable States state) {
        orderService.updateOrderState(orderId, state);
        return ResponseEntity.noContent().build(); 
    }


    
    @Operation(
        description = "Cancels an order. ROLE: CLIENT",
        summary = "Cancels an order",
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
    @PutMapping("cancel/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build(); 
    }
    
    
    @Operation(
        description = "obtains the order trace. ROLE: CLIENT",
        summary = "obtains the order trace",
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
    @GetMapping("/traceability/{restaurantId}")
    public ResponseEntity<OrderTraceabilityResponse> getOrderTrace(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(orderService.getOrderTraceability(restaurantId)); 
    }

    @Operation(
        description = "delivers an order by sumitting the PIN the user got. ROLE: EMPLOYEE",
        summary = "delivers an order",
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
    @PutMapping("/deliver/{orderId}/{pin}")
    public ResponseEntity<Void> deliverOrder(@PathVariable Long orderId, @PathVariable String pin) {
        orderService.deliverOrder(orderId, pin);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }
    
}
