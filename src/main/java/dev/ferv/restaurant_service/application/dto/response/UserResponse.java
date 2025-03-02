package dev.ferv.restaurant_service.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
}
