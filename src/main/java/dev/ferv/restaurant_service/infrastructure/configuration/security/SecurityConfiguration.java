package dev.ferv.restaurant_service.infrastructure.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dev.ferv.restaurant_service.domain.model.client.Role;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jAuthenticationFilter;


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
            .disable()
            )
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/restaurant/create").hasRole(Role.OWNER.name())
                .requestMatchers("/restaurant/employee").hasRole(Role.OWNER.name())
                .requestMatchers("/dish/create").hasRole(Role.OWNER.name())
                .requestMatchers("/order/create").hasRole(Role.CLIENT.name())
                .requestMatchers("/order/cancel").hasRole(Role.CLIENT.name())
                .requestMatchers("/order/sign").hasRole(Role.EMPLOYEE.name())
                .requestMatchers("/order/getByStateAndRestaurant").hasRole(Role.EMPLOYEE.name())
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)   
            )
            .addFilterBefore(jAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }


}
