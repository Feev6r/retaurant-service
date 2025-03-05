package dev.ferv.restaurant_service.domain.model.client;

public class SmsRequestClient {

    private final String phoneNumber;
    private final String message;
    
    public SmsRequestClient(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }
    
}
