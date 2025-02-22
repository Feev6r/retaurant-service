package dev.ferv.restaurant_service.infrastructure.configuration.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.ferv.restaurant_service.domain.port.out.IDishPort;
import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import dev.ferv.restaurant_service.domain.port.out.IOrderPort;
import dev.ferv.restaurant_service.domain.port.out.IRestaurantPort;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.DishAdapter;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.OrderAdapter;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.RestaurantAdapter;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.DishEntityMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.DishOrderMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.OrderEntityMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.PageMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.RestaurantEntityMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.CategoryRepository;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.DishOrderrRepository;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.DishRepository;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.OrderRepository;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.RestaurantRepository;
import dev.ferv.restaurant_service.infrastructure.adapter.security.JwtAdapter;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AdapterConfiguration {

    private final DishEntityMapper dishEntityMapper;
    private final DishRepository dishRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final CategoryRepository categoryRepository;
    private final DishOrderrRepository dishOrderrRepository;
    private final DishOrderMapper dishOrderMapper;
    private final PageMapper pageMapper;

    @Bean
    IJwtPort jwtPort(){
        return new JwtAdapter();
    }

    @Bean
    IDishPort dishPort(){
        return new DishAdapter(dishEntityMapper, dishRepository, restaurantRepository, pageMapper, categoryRepository, jwtPort());
    } 

    @Bean
    IRestaurantPort restaurantPort(){
        return new RestaurantAdapter(restaurantEntityMapper, restaurantRepository, pageMapper);
    }

    @Bean 
    IOrderPort orderPort(){
        return new OrderAdapter(orderRepository, orderEntityMapper, dishOrderrRepository, dishOrderMapper);
    }


}
