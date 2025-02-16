package dev.ferv.restaurant_service.infrastructure.exeptionHandler;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.ferv.restaurant_service.infrastructure.exeption.DishNotFoundExeption;
import dev.ferv.restaurant_service.infrastructure.exeption.NoDataFoundExeption;
import dev.ferv.restaurant_service.infrastructure.exeption.RestaurantNotFoundExeption;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";


    @ExceptionHandler(NoDataFoundExeption.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundExeption(NoDataFoundExeption noDataFoundExeption){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Collections.singletonMap(MESSAGE, ExeptionResponse.NO_DATA_FOUND.getMessage()));
    }

    
    @ExceptionHandler(DishNotFoundExeption.class)
    public ResponseEntity<Map<String, String>> handleTaskNotFoundExeption(DishNotFoundExeption taskNotFoundExeption){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Collections.singletonMap(MESSAGE, ExeptionResponse.DISH_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(RestaurantNotFoundExeption.class)
    public ResponseEntity<Map<String, String>> handlePhotoNotFoundExeption(RestaurantNotFoundExeption photoNotFoundExeption){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Collections.singletonMap(MESSAGE, ExeptionResponse.RESTAURANT_NOT_FOUND.getMessage()));
    }
    

}
