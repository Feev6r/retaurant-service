package dev.ferv.restaurant_service.domain.model.client;

public class UserClient {

    private Long id;
    private String email;
    private Role role;

    public UserClient() {
    }

    public UserClient(Long id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public String getUsername(){
        return email;
    }

    public Role getRole() {
        return role;
    }   
    
    public Long getId(){
        return id;
    }
}

