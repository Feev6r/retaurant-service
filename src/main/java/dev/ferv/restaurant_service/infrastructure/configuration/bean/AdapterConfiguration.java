package dev.ferv.restaurant_service.infrastructure.configuration.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.ferv.restaurant_service.domain.port.out.IDishPort;
import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import dev.ferv.restaurant_service.domain.port.out.IRestaurantPort;
import dev.ferv.restaurant_service.infrastructure.adapter.implementation.JwtAdapter;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.DishAdapter;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.RestaurantAdapter;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.DishEntityMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.PageMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.RestaurantEntityMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.DishRepository;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AdapterConfiguration {

    private final DishEntityMapper dishEntityMapper;
    private final DishRepository dishRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final RestaurantRepository restaurantRepository;
    private final PageMapper pageMapper;

    @Bean
    IJwtPort jwtPort(){
        return new JwtAdapter();
    }

    @Bean
    IDishPort dishPort(){
        return new DishAdapter(dishEntityMapper, dishRepository, restaurantRepository, pageMapper, jwtPort());
    } 

    @Bean
    IRestaurantPort restaurantPort(){
        return new RestaurantAdapter(restaurantEntityMapper, restaurantRepository, pageMapper);
    }


}
