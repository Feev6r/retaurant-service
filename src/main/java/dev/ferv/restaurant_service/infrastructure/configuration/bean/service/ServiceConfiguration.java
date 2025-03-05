package dev.ferv.restaurant_service.infrastructure.configuration.bean.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.ferv.restaurant_service.domain.port.out.ICourierClientPort;
import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import dev.ferv.restaurant_service.domain.port.out.IOrderPort;
import dev.ferv.restaurant_service.domain.port.out.IRestaurantPort;
import dev.ferv.restaurant_service.domain.port.out.ITraceabilityPort;
import dev.ferv.restaurant_service.domain.port.out.IUserClientPort;
import dev.ferv.restaurant_service.domain.service.IOrderDomainService;
import dev.ferv.restaurant_service.domain.service.OrderDomainService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ServiceConfiguration {

    private final IJwtPort jwtPort;
    private final IRestaurantPort restaurantPort;
    private final IOrderPort orderPort;
    private final ITraceabilityPort traceabilityPort;
    private final ICourierClientPort clientPort;
    private final IUserClientPort userClientPort;

    @Bean
    IOrderDomainService orderDomainService(){
        return new OrderDomainService(jwtPort, restaurantPort, orderPort,traceabilityPort,clientPort, userClientPort);
    }

}
