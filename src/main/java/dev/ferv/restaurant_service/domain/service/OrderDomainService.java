package dev.ferv.restaurant_service.domain.service;

import java.util.Arrays;
import java.util.List;

import dev.ferv.restaurant_service.domain.exeptions.stateExeptions.EmployeeNotFoundInTheRestaurantExeption;
import dev.ferv.restaurant_service.domain.exeptions.stateExeptions.NotValidPin;
import dev.ferv.restaurant_service.domain.exeptions.stateExeptions.OrderAlreadySignedExeption;
import dev.ferv.restaurant_service.domain.exeptions.stateExeptions.StateUpdateExeption;
import dev.ferv.restaurant_service.domain.exeptions.stateExeptions.UnfinishedOrderExeption;
import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.Restaurant;
import dev.ferv.restaurant_service.domain.model.States;
import dev.ferv.restaurant_service.domain.model.client.OrderTraceabilityClient;
import dev.ferv.restaurant_service.domain.model.client.SmsRequestClient;
import dev.ferv.restaurant_service.domain.model.client.UserClient;
import dev.ferv.restaurant_service.domain.model.client.ValidationResponse;
import dev.ferv.restaurant_service.domain.port.out.ICourierClientPort;
import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import dev.ferv.restaurant_service.domain.port.out.IOrderPort;
import dev.ferv.restaurant_service.domain.port.out.IRestaurantPort;
import dev.ferv.restaurant_service.domain.port.out.ITraceabilityPort;
import dev.ferv.restaurant_service.domain.port.out.IUserClientPort;
import dev.ferv.restaurant_service.infrastructure.configuration.security.SecurityContext;

public class OrderDomainService implements IOrderDomainService{

    private final IJwtPort jwtPort;
    private final IRestaurantPort restaurantPort;
    private final IOrderPort orderPort;
    private final ITraceabilityPort traceabilityPort;
    private final ICourierClientPort courierClientPort;
    private final IUserClientPort userClientPort;

    public OrderDomainService(IJwtPort jwtPort, IRestaurantPort restaurantPort, IOrderPort orderPort,
            ITraceabilityPort traceabilityPort, ICourierClientPort courierClientPort, IUserClientPort userClientPort) {
        this.jwtPort = jwtPort;
        this.restaurantPort = restaurantPort;
        this.orderPort = orderPort;
        this.traceabilityPort = traceabilityPort;
        this.courierClientPort = courierClientPort;
        this.userClientPort = userClientPort;
    }


    @Override
    public Order createOrder(Order order) {

        List<States> states = Arrays.asList(
        States.PENDING,
        States.PREPARING,
        States.READY
        );

        if(orderPort.hasClientAnOrderUnfinished(order.getClientId(), states)){
            throw new UnfinishedOrderExeption();
        }

        return orderPort.createOrder(order);
    }


    @Override
    public void signOrder(Long orderId){


        Order order = orderPort.getOrderById(orderId);
        Restaurant restaurant = restaurantPort.getById(order.getRestaurantId()); //get the restaurant from which the order was placed

        Long employeeId = jwtPort.getIdBySecurityContext();

        if(order.getState() == States.CANCELLED)
            throw new StateUpdateExeption();
        if(!restaurant.getEmployeesId().contains(employeeId)) 
            throw new EmployeeNotFoundInTheRestaurantExeption();
        if(order.getAssignedEmployeeId() != null) 
            throw new OrderAlreadySignedExeption();

        order.setAssignedEmployeeId(employeeId);
        orderPort.updateOrder(order);
    }

    @Override
    public void setOrderAsDelivered(Long orderId, String code){

        Order order = orderPort.getOrderById(orderId);

        UserClient userClient = userClientPort.getUserContactById(order.getClientId(), "Bearer " + SecurityContext.getToken());
        userClient.getPhoneNumber();


        String phoneNumber = "+573127598294";
        ValidationResponse validationResponse = courierClientPort.verifyCode(phoneNumber, code);
        // if(validationResponse == null) System.out.println("ES NULOOOOO NULOOO NULLOOOOO ------------------");
        System.out.println(validationResponse.isValid() + " " + validationResponse.getMessage() + " " + validationResponse.getErrorCode());
        if(validationResponse.isValid() == false) throw new NotValidPin();
     

        Order newOrderProperties = new Order();
        newOrderProperties.setId(orderId);
        newOrderProperties.setState(States.DELIVERED);

        orderPort.updateOrder(newOrderProperties);

    }


    @Override
    public void updateState(Long orderId, States state){
        Order order = orderPort.getOrderById(orderId);

        if(order.getState() == States.CANCELLED || order.getState() == States.DELIVERED)
            throw new StateUpdateExeption();
        if(order.getState().getValue() >= state.getValue()) 
            throw new StateUpdateExeption();
        if(Math.abs(state.getValue() - order.getState().getValue()) > 1) 
            throw new StateUpdateExeption();
            
        if(state == States.READY){
            
            //in this case the phone number is just mine because of the twilio free tier limitation, *but if we dont keep that in mind*, this implementation will work with any number 
            UserClient userClient = userClientPort.getUserContactById(order.getClientId(), "Bearer " + SecurityContext.getToken());
            userClient.getPhoneNumber();

            String message = "Your order is ready! This is the PIN to claim the order: ";
            courierClientPort.sendSms(new SmsRequestClient("+573127598294", message));
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
            throw new StateUpdateExeption();
        }

        Order newOrderProperties = new Order();
        newOrderProperties.setId(orderId);
        newOrderProperties.setState(States.CANCELLED);

        orderPort.updateOrder(newOrderProperties);
    }


    @Override
    public OrderTraceabilityClient getOrderTraceability(Long restaurantId, String jwt){
 
        List<States> states = Arrays.asList(
        States.PENDING,
        States.PREPARING,
        States.READY
        );

        Long orderId = orderPort.getCurrentOrder(jwtPort.getIdBySecurityContext(), restaurantId, states);

        if(orderId == null){
            throw new RuntimeException("The current user does not have an active order");
        }

        return traceabilityPort.getOrderTraceability(orderId, jwt);        
    }

    
}
