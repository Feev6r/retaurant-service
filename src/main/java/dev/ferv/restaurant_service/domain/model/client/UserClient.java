package dev.ferv.restaurant_service.domain.model.client;

public class UserClient {

    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;

    public UserClient(String firstname, String lastname, String email, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}

