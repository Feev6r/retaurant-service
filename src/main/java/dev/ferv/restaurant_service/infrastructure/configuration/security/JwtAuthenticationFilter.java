package dev.ferv.restaurant_service.infrastructure.configuration.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.ferv.restaurant_service.domain.model.client.UserClient;
import dev.ferv.restaurant_service.domain.port.out.IUserClientPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final IUserClientPort userClientPort;

    @Override
    protected void doFilterInternal(
        @SuppressWarnings("null") HttpServletRequest request, 
        @SuppressWarnings("null") HttpServletResponse response, 
        @SuppressWarnings("null") FilterChain filterChain)
            throws ServletException, IOException {
  
        String jwt = extractJwtFromRequest(request);

        if (jwt != null) {

            UserClient user = userClientPort.getUser("Bearer " + jwt); //get user from the user-service (microservice) 

            if (user != null) { //if it is null that means the jwt was not valid 

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(  
                        user.getUsername(), //set the username (email in this case)
                        null, 
                        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
                );

                auth.setDetails(new HashMap<String, String>() {{ //add the id to the details, we'll need this information later
                    put("id", user.getId().toString());
                }});

                SecurityContextHolder.getContext().setAuthentication(auth); //set it to the context
            }
        }

        filterChain.doFilter(request, response);

    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
