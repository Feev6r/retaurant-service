package dev.ferv.restaurant_service.domain.port.out;

import dev.ferv.restaurant_service.domain.model.client.UserClient;

public interface IUserClientPort {
        
    UserClient getUser(String jwt); 
    UserClient getUserById(Long id, String jwt);
}
