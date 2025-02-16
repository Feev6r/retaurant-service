package dev.ferv.restaurant_service.infrastructure.adapter.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import dev.ferv.restaurant_service.domain.model.client.UserClient;
import dev.ferv.restaurant_service.domain.port.out.IUserClientPort;

@FeignClient(name = "user-service")
public interface UserClientAdapter extends IUserClientPort{

    @GetMapping("user/get")
    UserClient getUser(@RequestHeader("Authorization") String jwt); 

}
