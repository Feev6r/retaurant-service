package dev.ferv.restaurant_service.infrastructure.adapter.jpa;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import dev.ferv.restaurant_service.domain.model.Dish;
import dev.ferv.restaurant_service.domain.model.PageResult;
import dev.ferv.restaurant_service.domain.port.out.IDishPort;
import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.CategoryEntity;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.DishEntity;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.RestaurantEntity;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.DishEntityMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper.PageMapper;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.CategoryRepository;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.DishRepository;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.repository.RestaurantRepository;
import dev.ferv.restaurant_service.infrastructure.exeption.DishNotFoundExeption;
import dev.ferv.restaurant_service.infrastructure.exeption.RestaurantNotFoundExeption;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishAdapter implements IDishPort{

    private final DishEntityMapper dishEntityMapper;
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final PageMapper pageMapper;
    private final CategoryRepository categoryRepository;
    private final IJwtPort jwtPort;

    @Override
    public void saveDish(Dish dish) {

        Long userId = jwtPort.getIdBySecurityContext();
        RestaurantEntity restaurantEntity = restaurantRepository.findByOwnerId(userId)
            .orElseThrow(RestaurantNotFoundExeption::new);

        DishEntity dishEntity = dishEntityMapper.toEntity(dish);


        //if the category already exists, set it with that one
        CategoryEntity category = categoryRepository.findByName(dishEntity.getCategory().getName())
            .orElse(null);
        if(category != null){
            dishEntity.setCategory(category);
        }

        dishEntity.setRestaurant(restaurantEntity);
        restaurantEntity.getDishes().add(dishEntity); //update restaurant with the new dish

        dishRepository.save(dishEntity); //first we save the dish then the restaurantant
        restaurantRepository.save(restaurantEntity); //save the changes

    }

    @Override
    public void updateDish(Dish dishChanges){

        DishEntity dishEntity = dishRepository.findById(dishChanges.getId())
            .orElseThrow(DishNotFoundExeption::new); //verify if the id exists, otherwise throw a delicious notfound error 

        
        if(dishChanges.getPrice() != null){
            dishEntity.setPrice(dishChanges.getPrice());
        }
        if(dishChanges.getDescription() != null){
            dishEntity.setDescription(dishChanges.getDescription());
        }
        if(dishChanges.isAvailable() != dishEntity.isAvailable()){

            RestaurantEntity restaurantEntity = restaurantRepository.findByOwnerId(jwtPort.getIdBySecurityContext())
            .orElseThrow(RestaurantNotFoundExeption::new);

            if(dishEntity.isAvailable() == true){ //if it was true, that means the change was set to false because their different 
                restaurantEntity.getDishes().removeIf(dish -> dish.getId() == dishChanges.getId()); //remove the dish that now it isnt available 
                dishEntity.setRestaurant(null);
            }
            else{
                restaurantEntity.getDishes().add(dishEntity);
                dishEntity.setRestaurant(restaurantEntity);
            }

            dishEntity.setAvailable(dishChanges.isAvailable());
            restaurantRepository.save(restaurantEntity); //update restaurant (dont forget)
        }

        dishRepository.save(dishEntity);

    }

    @Override
    public Dish getDishById(Long id){
        return dishEntityMapper.toDish(dishRepository.findById(id).orElseThrow(DishNotFoundExeption::new));
    }


    @Override
    public PageResult<Dish> getByRestaurantId(Long restaurantId, int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<DishEntity> pageDishEntity = dishRepository.findByRestaurantId(restaurantId, pageable);

        return pageMapper.toPageResultDish(pageDishEntity);
    }

    @Override
    public PageResult<Dish> getDishByCategory(String name, int page, int size) {
        Page<DishEntity> pageDishEntity = dishRepository.findByCategoryName(name, PageRequest.of(page, size));

        return pageMapper.toPageResultDish(pageDishEntity);
    }

    @Override
    public List<Dish> getAllById(List<Long> ids){
        List<DishEntity> dishEntities = dishRepository.findAllById(ids);
        return dishEntityMapper.toDishesList(dishEntities);
    }

}
