package dev.ferv.restaurant_service.application.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.ferv.restaurant_service.application.dto.request.RestaurantRequest;
import dev.ferv.restaurant_service.application.dto.response.RestaurantResponse;
import dev.ferv.restaurant_service.application.mapper.PageDtoMapper;
import dev.ferv.restaurant_service.application.mapper.RestaurantMapper;
import dev.ferv.restaurant_service.application.service.interfaces.IRestaurantService;
import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.model.Restaurant;
import dev.ferv.restaurant_service.domain.port.in.restaurant.ICreateRestaurantUseCase;
import dev.ferv.restaurant_service.domain.port.in.restaurant.IGetRestaurantUseCase;
import dev.ferv.restaurant_service.domain.port.in.restaurant.ISetEmployeesUseCase;

@Service
public class RestaurantService implements IRestaurantService{

    private final ICreateRestaurantUseCase createRestaurantUseCase;
    private final IGetRestaurantUseCase getRestaurantsUseCase;
    private final ISetEmployeesUseCase setEmployeesUseCase;
    private final RestaurantMapper restaurantMapper;
    private final PageDtoMapper pageDtoMapper;

    
    public RestaurantService(ICreateRestaurantUseCase createRestaurantUseCase,
            IGetRestaurantUseCase getRestaurantsUseCase, ISetEmployeesUseCase setEmployeesUseCase,
            RestaurantMapper restaurantMapper, PageDtoMapper pageDtoMapper) {
        this.createRestaurantUseCase = createRestaurantUseCase;
        this.getRestaurantsUseCase = getRestaurantsUseCase;
        this.setEmployeesUseCase = setEmployeesUseCase;
        this.restaurantMapper = restaurantMapper;
        this.pageDtoMapper = pageDtoMapper;
    }

    @Override
    @Transactional
    public void createRestaurant(RestaurantRequest restaurantRequest) { 
        Restaurant restaurant = restaurantMapper.toRestaurant(restaurantRequest);
        createRestaurantUseCase.createRestaurant(restaurant);
    }

    @Override
    public Page<RestaurantResponse> getRestaurants(int page, int size) {

        PageResult<Restaurant> restaurantResponses = getRestaurantsUseCase.getRestaurants(page, size);
        return pageDtoMapper.toPageRestaurantResponse(restaurantResponses);
    }

    @Override
    @Transactional
    public void setEmployees(List<Long> ids) {
        setEmployeesUseCase.setEmployee(ids);
    }

}
