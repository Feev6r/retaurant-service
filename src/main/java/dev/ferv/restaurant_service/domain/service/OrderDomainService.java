package dev.ferv.restaurant_service.domain.service;


import java.util.Arrays;
import java.util.List;

import dev.ferv.restaurant_service.domain.exeptions.EmployeeDoesNotExistInTheRestaurant;
import dev.ferv.restaurant_service.domain.exeptions.OrderIsAlreadySigned;
import dev.ferv.restaurant_service.domain.exeptions.StateExeption;
import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.Restaurant;
import dev.ferv.restaurant_service.domain.model.States;
import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import dev.ferv.restaurant_service.domain.port.out.IOrderPort;
import dev.ferv.restaurant_service.domain.port.out.IRestaurantPort;

public class OrderDomainService implements IOrderDomainService{

    private final IJwtPort jwtPort;
    private final IRestaurantPort restaurantPort;
    private final IOrderPort orderPort;

    public OrderDomainService(IJwtPort jwtPort, IRestaurantPort restaurantPort, IOrderPort orderPort) {
        this.jwtPort = jwtPort;
        this.restaurantPort = restaurantPort;
        this.orderPort = orderPort;
    }

    @Override
    public Order createOrder(Order order) {

        List<States> states = Arrays.asList(
        States.PENDING,
        States.PREPARING,
        States.READY
        );

        if(orderPort.hasClientAnOrderUnfinished(order.getClientId(), states)){
            throw new RuntimeException("Cannot create another order because there are at least one order that hasnt finished yet");
            //TODO CUSTOM EXEPTION HERE
        }

        return orderPort.createOrder(order);
    }


    @Override
    public void signOrder(Long orderId){


        Order order = orderPort.getOrderById(orderId);
        Restaurant restaurant = restaurantPort.getById(order.getRestaurantId()); //get the restaurant from which the order was placed

        Long employeeId = jwtPort.getIdBySecurityContext();

        if(order.getState() == States.CANCELLED)
            throw new StateExeption("The is order is cancelled and can not be signed");
        if(!restaurant.getEmployeesId().contains(employeeId)) 
            throw new EmployeeDoesNotExistInTheRestaurant("The employee does not work in current restaurant with the order id: " + orderId);
        if(order.getAssignedEmployeeId() != null) 
            throw new OrderIsAlreadySigned("The order is already signed");

        order.setAssignedEmployeeId(employeeId);
        orderPort.updateOrder(order);
    }

    @Override
    public void updateState(Long orderId, States state){
        Order order = orderPort.getOrderById(orderId);

        if(order.getState() == States.CANCELLED)
            throw new StateExeption("The is order is cancelled and its state can not be changed");
        if(order.getState().getValue() >= state.getValue()) 
            throw new StateExeption("The state cannot be changed backwards or be the same");
        if(Math.abs(state.getValue() - order.getState().getValue()) > 1) 
            throw new StateExeption("The state can not be changed by skipping previous states");
            
        if(state == States.READY){
            //TODO FETCH THE COURIER SERVICE TO GENERATE THE PIN TO CONTINUE
        }

        Order newOrderProperties = new Order();
        newOrderProperties.setId(orderId);
        newOrderProperties.setState(state);

        orderPort.updateOrder(newOrderProperties);
    }

    @Override
    public void cancelOrder(Long orderId){

        Order order = orderPort.getOrderById(orderId);
        if(order.getState() != States.PENDING){
            throw new StateExeption("The order cannot be cancelled once it has been prepared or is being prepared");
        }

        Order newOrderProperties = new Order();
        newOrderProperties.setId(orderId);
        newOrderProperties.setState(States.CANCELLED);

        orderPort.updateOrder(newOrderProperties);
    }


    
}
