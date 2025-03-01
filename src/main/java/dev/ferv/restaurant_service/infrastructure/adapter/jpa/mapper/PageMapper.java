package dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import dev.ferv.restaurant_service.domain.model.Dish;
import dev.ferv.restaurant_service.domain.model.Order;
import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.model.Restaurant;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.DishEntity;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.RestaurantEntity;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.order.OrderEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PageMapper {

    private final DishEntityMapper dishEntityMapper;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final OrderEntityMapper orderEntityMapper;

    public Page<Dish> toPageDish(Page<DishEntity> pageDishEntity){

        List<Dish> dishList = pageDishEntity.getContent().stream()
            .map(dishEntityMapper::toDish)
            .collect(Collectors.toList());

        return new PageImpl<>(dishList, pageDishEntity.getPageable(), pageDishEntity.getTotalElements());
    }

    public Page<Restaurant> toPageRestaurant(Page<RestaurantEntity> pageRestaurantEntity){

        List<Restaurant> restaurantList = pageRestaurantEntity.getContent().stream()
        .map(restaurantEntityMapper::toRestaurant)
        .collect(Collectors.toList());

        return new PageImpl<>(restaurantList, pageRestaurantEntity.getPageable(), pageRestaurantEntity.getTotalElements());

    }

    public Page<Order> toPageOrder(Page<OrderEntity> pageOrderEntity){

        List<Order> orderList = pageOrderEntity.getContent().stream()
        .map(orderEntityMapper::toOrder)
        .collect(Collectors.toList());

        return new PageImpl<>(orderList, pageOrderEntity.getPageable(), pageOrderEntity.getTotalElements());
    }

    public PageResult<Order> toPageResultOrder(Page<OrderEntity> pageOrderEntity){

        Page<Order> pageOrder = toPageOrder(pageOrderEntity);

        return new PageResult<>(
            pageOrder.getContent(),
            pageOrder.getNumber(),
            pageOrder.getSize(),
            pageOrder.getTotalElements()
        );
    }


    public PageResult<Dish> toPageResultDish(Page<DishEntity> pageDishEntity){

        Page<Dish> pageDish = toPageDish(pageDishEntity);

        return new PageResult<>(
            pageDish.getContent(),
            pageDish.getNumber(),
            pageDish.getSize(),
            pageDish.getTotalElements()
        );
    }


    public PageResult<Restaurant> toPageResultRestaurant(Page<RestaurantEntity> pageRestaurantEntity){

        Page<Restaurant> pageRestaurant =  toPageRestaurant(pageRestaurantEntity);

        return new PageResult<>(
            pageRestaurant.getContent(),
            pageRestaurant.getNumber(),
            pageRestaurant.getSize(),
            pageRestaurant.getTotalElements()
        );
    }

}
