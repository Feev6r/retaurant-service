package dev.ferv.restaurant_service.infrastructure.adapter.jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import dev.ferv.restaurant_service.domain.model.DishOrder;
import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.order.DishOrderEntity;

@Mapper(componentModel = "spring", uses = DishEntityMapper.class )
public interface DishOrderMapper {

    DishOrderEntity toEntity(DishOrder dishOrder);
    List<DishOrderEntity> toEntityList(List<DishOrder> dishOrderList);
}
