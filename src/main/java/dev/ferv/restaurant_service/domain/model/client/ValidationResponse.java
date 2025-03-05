package dev.ferv.restaurant_service.domain.model.client;

public class ValidationResponse {

    private boolean isValid;
    private String message;
    private String errorCode;

    public ValidationResponse(boolean isValid, String message, String errorCode) {
        this.isValid = isValid;
        this.message = message;
        this.errorCode = errorCode;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isValid() {
        return isValid;
    }
    public String getMessage() {
        return message;
    }
    public String getErrorCode() {
        return errorCode;
    }

}
