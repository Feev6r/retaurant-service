package dev.ferv.restaurant_service.infrastructure.adapter.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import dev.ferv.restaurant_service.domain.port.out.IJwtPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtAdapter implements IJwtPort{

    @Value("${my-app.security.jwt.secret-key}")
    private String secretKey;

    @Override
    public Long getIdBySecurityContext(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // contexto de seguridad
        return Long.parseLong(authentication.getName());       
    }


    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); // :: para invocar a un metodo como expresion lambda en este caso
    }


    @Override
    public String extractRoles(String jwt){
       Claims claims = extractAllClaims(jwt);
       String role =  claims.get("roles", String.class);
       return role;
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

         Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(getSignInKey()) // verifica la signature
                .build()
                .parseSignedClaims(token); // analizamos los claims

        return claimsJws.getPayload(); // devolvermos los claims
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }


    @Override
    public boolean isTokenValid(String token, String username) {

        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


}
