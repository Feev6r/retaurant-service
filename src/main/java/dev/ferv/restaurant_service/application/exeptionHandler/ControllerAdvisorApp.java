package dev.ferv.restaurant_service.application.exeptionHandler;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.ferv.restaurant_service.domain.exeptions.stateExeptions.EmployeeNotFoundInTheRestaurantExeption;
import dev.ferv.restaurant_service.domain.exeptions.stateExeptions.NotCurrentOrderFound;
import dev.ferv.restaurant_service.domain.exeptions.stateExeptions.NotValidPin;
import dev.ferv.restaurant_service.domain.exeptions.stateExeptions.OrderAlreadySignedExeption;
import dev.ferv.restaurant_service.domain.exeptions.stateExeptions.RestaurantNotBelongsToOwnerExeption;
import dev.ferv.restaurant_service.domain.exeptions.stateExeptions.StateUpdateExeption;
import dev.ferv.restaurant_service.domain.exeptions.stateExeptions.UnfinishedOrderExeption;


@ControllerAdvice
public class ControllerAdvisorApp {

    private static final String MESSAGE = "Message";

    
    @ExceptionHandler(EmployeeNotFoundInTheRestaurantExeption.class)
    public ResponseEntity<Map<String, String>> handleEmployeeNotFoundInTheRestaurantExeption(EmployeeNotFoundInTheRestaurantExeption exeption){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Collections.singletonMap(MESSAGE, ExeptionResponseApp.EMPLOYEE_DOES_NOT_EXIST_IN_THE_RESTAURANT.getMessage()));
    }

    @ExceptionHandler(NotCurrentOrderFound.class)
    public ResponseEntity<Map<String, String>> handleNotCurrentOrderFound(NotCurrentOrderFound exeption){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Collections.singletonMap(MESSAGE, ExeptionResponseApp.NOT_CURRENT_ORDER_FOUND.getMessage()));
    }

    @ExceptionHandler(StateUpdateExeption.class)
    public ResponseEntity<Map<String, String>> handleStateUpdateExeption(StateUpdateExeption exeption){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Collections.singletonMap(MESSAGE, ExeptionResponseApp.STATE_INVALID_UPDATE.getMessage()));
    }
    
    @ExceptionHandler(UnfinishedOrderExeption.class)
    public ResponseEntity<Map<String, String>> handleUnfinishedOrderExeption(UnfinishedOrderExeption exeption){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Collections.singletonMap(MESSAGE, ExeptionResponseApp.UNFINISHED_ORDER.getMessage()));
    }

    @ExceptionHandler(OrderAlreadySignedExeption.class)
    public ResponseEntity<Map<String, String>> handleOrderAlreadySignedExeption(OrderAlreadySignedExeption exeption){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Collections.singletonMap(MESSAGE, ExeptionResponseApp.ORDER_ALREADY_SIGNED.getMessage()));
    }
    @ExceptionHandler(RestaurantNotBelongsToOwnerExeption.class)
    public ResponseEntity<Map<String, String>> handleRestaurantNotBelongsToOwnerExeption(RestaurantNotBelongsToOwnerExeption exeption){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Collections.singletonMap(MESSAGE, ExeptionResponseApp.RESTAURANT_NOT_BELONGS.getMessage()));
    }
    @ExceptionHandler(NotValidPin.class)
    public ResponseEntity<Map<String, String>> handleNotValidPin(NotValidPin exeption){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Collections.singletonMap(MESSAGE, ExeptionResponseApp.NOT_VALID_PIN.getMessage()));
    }


}
