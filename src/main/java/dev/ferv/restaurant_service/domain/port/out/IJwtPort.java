package dev.ferv.restaurant_service.domain.port.out;

public interface IJwtPort {

    Long getIdBySecurityContext();
    boolean isTokenValid(String token, String username);
    String extractUsername(String token); 
    String extractRoles(String jwt);

}
