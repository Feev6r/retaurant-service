package dev.ferv.restaurant_service.application.mapper;

import org.mapstruct.Mapper;

import dev.ferv.restaurant_service.application.dto.response.UserResponse;
import dev.ferv.restaurant_service.domain.model.client.UserClient;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponse toResponse(UserClient userClient);

}
