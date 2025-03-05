package dev.ferv.restaurant_service.infrastructure.adapter.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.ferv.restaurant_service.domain.model.client.SmsRequestClient;
import dev.ferv.restaurant_service.domain.model.client.ValidationResponse;
import dev.ferv.restaurant_service.domain.port.out.ICourierClientPort;

@FeignClient(name = "courier-service")
public interface CourierClientAdapter extends ICourierClientPort {

    @PostMapping("sms/")
    void sendSms(@RequestBody SmsRequestClient smsRequestClient);
    
    @GetMapping("sms/verify/{phoneNumber}/{code}")
    ValidationResponse verifyCode(@PathVariable String phoneNumber, @PathVariable String code);

}
