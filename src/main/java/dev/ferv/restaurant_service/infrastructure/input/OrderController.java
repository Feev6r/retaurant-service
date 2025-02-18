package dev.ferv.restaurant_service.infrastructure.input;

import org.springframework.web.bind.annotation.RestController;

import dev.ferv.restaurant_service.application.dto.request.OrderRequest;
import dev.ferv.restaurant_service.application.service.interfaces.IOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("order")
@Validated
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Void> postMethodName(@Valid @RequestBody OrderRequest request) {
        orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build(); 
    }
    
    
}
