package dev.ferv.restaurant_service.application.service;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.ferv.restaurant_service.application.dto.request.DishRequest;
import dev.ferv.restaurant_service.application.dto.request.DishUpdateRequest;
import dev.ferv.restaurant_service.application.dto.response.DishResponse;
import dev.ferv.restaurant_service.application.mapper.DishMapper;
import dev.ferv.restaurant_service.application.mapper.PageDtoMapper;
import dev.ferv.restaurant_service.application.service.interfaces.IDishService;
import dev.ferv.restaurant_service.domain.model.Dish;
import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.port.in.dish.ICreateDishUseCase;
import dev.ferv.restaurant_service.domain.port.in.dish.IGetDishesUseCase;
import dev.ferv.restaurant_service.domain.port.in.dish.IUpdateDishUseCase;

@Service
public class DishService implements IDishService{

    private final ICreateDishUseCase createDishUseCase;
    private final IUpdateDishUseCase updateDishUseCase;
    private final IGetDishesUseCase getDishesUseCase;
    private final DishMapper dishMapper;
    private final PageDtoMapper pageDtoMapper;
    
    public DishService(ICreateDishUseCase createDishUseCase, IUpdateDishUseCase updateDishUseCase,
            IGetDishesUseCase getDishesUseCase, DishMapper dishMapper, PageDtoMapper pageDtoMapper) {
        this.createDishUseCase = createDishUseCase;
        this.updateDishUseCase = updateDishUseCase;
        this.getDishesUseCase = getDishesUseCase;
        this.dishMapper = dishMapper;
        this.pageDtoMapper = pageDtoMapper;
    }

    @Override
    @Transactional
    public void createDish(DishRequest dishRequest) {

        Dish dish = dishMapper.toDish(dishRequest);
        createDishUseCase.CreateDish(dish);
    }

    @Override
    public Page<DishResponse> getDishes(Long restaurantId, int page, int size) {
        PageResult<Dish> pageResultDish = getDishesUseCase.getDishes(restaurantId, page, size);
        return pageDtoMapper.toPageDishResponse(pageResultDish);
        
    }

    @Override
    @Transactional
    public void updateDish(DishUpdateRequest dishUpdateRequest) {
         
        Dish dish = getDishesUseCase.getDish(dishUpdateRequest.getId());
        dishMapper.updateDishFromDto(dishUpdateRequest, dish);
        updateDishUseCase.uptdateDish(dish);
    }

    @Override
    public Page<DishResponse> getDishesByCategory(String category, int page, int size) {
        PageResult<Dish> pageResultDish = getDishesUseCase.getByCategory(category, page, size);
        return pageDtoMapper.toPageDishResponse(pageResultDish);
    }


}
