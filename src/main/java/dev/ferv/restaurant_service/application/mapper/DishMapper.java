package dev.ferv.restaurant_service.application.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import dev.ferv.restaurant_service.application.dto.request.DishRequest;
import dev.ferv.restaurant_service.application.dto.request.DishUpdateRequest;
import dev.ferv.restaurant_service.application.dto.response.DishResponse;
import dev.ferv.restaurant_service.domain.model.Category;
import dev.ferv.restaurant_service.domain.model.Dish;


@Mapper(componentModel = "spring", 
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishMapper {
   
    @Mapping(target = "category", qualifiedByName = "toCategory")
    Dish toDish(DishRequest dishRequest);

    DishResponse toResponse(Dish dish);

    List<DishResponse> toResponselList(List<Dish> dishes);


    @Named("toCategory")
    static Category toCategory(String name){
        Category category = new Category(null, name);
        return category;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) //update ignoring the null values
    void updateDishFromDto(DishUpdateRequest dishUpdateRequest, @MappingTarget Dish dish);
}
