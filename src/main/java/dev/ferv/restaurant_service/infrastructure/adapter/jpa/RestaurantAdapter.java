package dev.ferv.restaurant_service.infrastructure.adapter.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.model.Restaurant;
import dev.ferv.restaurant_service.domain.port.out.IRestaurantPort;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.RestaurantEntity;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.PageMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.RestaurantEntityMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.RestaurantRepository;
import dev.ferv.restaurant_service.infrastructure.exeption.RestaurantNotFoundExeption;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantAdapter implements IRestaurantPort{

    private final RestaurantEntityMapper restaurantEntityMapper;
    private final RestaurantRepository restaurantRepository;
    private final PageMapper pageMapper;

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public PageResult<Restaurant> getAllRestaurant(int page, int size) {

        Page<RestaurantEntity> pageRestaurantEntity = restaurantRepository.findAll(PageRequest.of(page, size));
        return pageMapper.toPageResultRestaurant(pageRestaurantEntity);
    }

    @Override
    public Restaurant getByOwnerId(Long id) {
        return restaurantEntityMapper.toRestaurant(restaurantRepository.findByOwnerId(id)
            .orElseThrow(RestaurantNotFoundExeption::new));
    }

    @Override
    public Restaurant getById(Long id) {
        return restaurantEntityMapper.toRestaurant(restaurantRepository.findById(id)
        .orElseThrow(RestaurantNotFoundExeption::new));
    }

    

}
