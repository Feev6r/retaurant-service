package dev.ferv.restaurant_service.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.application.dto.response.DishResponse;
import dev.ferv.restaurant_service.application.dto.response.OrderResponse;
import dev.ferv.restaurant_service.application.dto.response.RestaurantResponse;
import dev.ferv.restaurant_service.domain.model.Dish;
import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PageDtoMapper {

    private final DishMapper dishMapper;
    private final RestaurantMapper restaurantMapper;
    private final OrderResponseMapper orderResponseMapper;

    public Page<DishResponse> toPageDishResponse(PageResult<Dish> pageResult){

         List<DishResponse> dishResponseList = pageResult.getContent().stream()
            .map(dishMapper::toResponse).collect(Collectors.toList());

        return new PageImpl<>(dishResponseList, PageRequest.of(pageResult.getPage(), pageResult.getSize()), pageResult.getTotalElements());
    }

    
    public Page<RestaurantResponse> toPageRestaurantResponse(PageResult<Restaurant> pageResult){

        List<RestaurantResponse> RestaurantResponseList = pageResult.getContent().stream()
           .map(restaurantMapper::toResponse).collect(Collectors.toList());

       return new PageImpl<>(RestaurantResponseList, PageRequest.of(pageResult.getPage(), pageResult.getSize()), pageResult.getTotalElements());
    }
    
    public Page<OrderResponse> toPageOrderResponse(PageResult<Order> pageResult){

        List<OrderResponse> orderResponseList = pageResult.getContent().stream()
           .map(orderResponseMapper::toResponse).collect(Collectors.toList());

       return new PageImpl<>(orderResponseList, PageRequest.of(pageResult.getPage(), pageResult.getSize()), pageResult.getTotalElements());
    }
}

