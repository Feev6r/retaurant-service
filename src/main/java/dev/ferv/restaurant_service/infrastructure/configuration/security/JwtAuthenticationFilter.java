package dev.ferv.restaurant_service.infrastructure.configuration.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final IJwtPort jwtPort;

    @Override
    protected void doFilterInternal(
            @SuppressWarnings("null") HttpServletRequest request,
            @SuppressWarnings("null") HttpServletResponse response,
            @SuppressWarnings("null") FilterChain filterChain)
            throws ServletException, IOException {

        try {

            String path = request.getRequestURI();

            if (path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui")) {
                filterChain.doFilter(request, response); // No aplica autenticación
                return;
            }

            String jwt = extractJwtFromRequest(request);

            if (jwt != null) {

                String username = jwtPort.extractUsername(jwt);
                String role = jwtPort.extractRoles(jwt);

                SecurityContext.setToken(jwt); // store the jwt during the request

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, // set the username (email in this case)
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_" + role)));

                SecurityContextHolder.getContext().setAuthentication(auth); // set it to the context
            } 
            else { // JWT was invalid or not found
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, e.getMessage()); // 503 Unavailable
            System.out.println(e.getMessage());
            return;
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            SecurityContext.clear();
        }

    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
