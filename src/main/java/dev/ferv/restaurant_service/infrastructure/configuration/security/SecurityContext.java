package dev.ferv.restaurant_service.infrastructure.configuration.security;

public class SecurityContext {
    
    // threadlocal to store the token in the context
    private static final ThreadLocal<String> tokenHolder = new ThreadLocal<>();

    public static void setToken(String token) {
        tokenHolder.set(token);
    }
    public static String getToken() {
        return tokenHolder.get();
    }

    // clean the holder to avoid memory leaks
    public static void clear() {
        tokenHolder.remove();
    }
}