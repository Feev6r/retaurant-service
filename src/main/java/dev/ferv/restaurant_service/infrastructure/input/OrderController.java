package dev.ferv.restaurant_service.infrastructure.input;

import org.springframework.web.bind.annotation.RestController;

import dev.ferv.restaurant_service.application.dto.request.OrderRequest;
import dev.ferv.restaurant_service.application.dto.response.OrderResponse;
import dev.ferv.restaurant_service.application.service.interfaces.IOrderService;
import dev.ferv.restaurant_service.domain.model.States;
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

    @PostMapping("/create")
    public ResponseEntity<Void> makeOrder(@Valid @RequestBody OrderRequest request) {
        orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build(); 
    }

    @GetMapping("/getByStateAndRestaurant/{state}/{restaurantId}")
    public ResponseEntity<Page<OrderResponse>> getMethodName(
    
    @PathVariable States state, 
    @PathVariable Long restaurantId, 
    @RequestParam(defaultValue = "0") @Min(0) int page, 
    @RequestParam(defaultValue = "5") @Min(1) int size) {
        
        return ResponseEntity.ok(orderService.getOrdersByRestaurantIdAndState(restaurantId, state, page, size));
    }
    

    @PutMapping("sign/{orderId}")
    public ResponseEntity<Void> signOrder(@PathVariable Long orderId) {
        orderService.signOrder(orderId);
        return ResponseEntity.noContent().build(); 
    }

    @PutMapping("update/{orderId}/{state}")
    public ResponseEntity<Void> updateOrder(@PathVariable Long orderId, @PathVariable States state) {
        orderService.updateOrderState(orderId, state);
        return ResponseEntity.noContent().build(); 
    }

    @PutMapping("cancel/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build(); 
    }
     
}
