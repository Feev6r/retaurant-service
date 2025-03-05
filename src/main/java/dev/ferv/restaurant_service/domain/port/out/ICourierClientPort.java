package dev.ferv.restaurant_service.domain.port.out;

import dev.ferv.restaurant_service.domain.model.client.SmsRequestClient;
import dev.ferv.restaurant_service.domain.model.client.ValidationResponse;

public interface ICourierClientPort {

    void sendSms(SmsRequestClient smsRequestClient);
    ValidationResponse verifyCode(String phoneNumber, String code);

}
