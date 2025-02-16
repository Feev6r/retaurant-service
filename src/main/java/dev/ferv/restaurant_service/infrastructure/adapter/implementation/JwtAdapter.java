package dev.ferv.restaurant_service.infrastructure.adapter.implementation;

import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import dev.ferv.restaurant_service.domain.port.out.IJwtPort;

public class JwtAdapter implements IJwtPort{

    @Override
    public Long getIdBySecurityContext(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // contexto de seguridad

        @SuppressWarnings("unchecked")
        Map<String, String> details = (Map<String, String>) authentication.getDetails();

        Long id = Long.parseLong(details.get("id"));

        return id;
    }

}
