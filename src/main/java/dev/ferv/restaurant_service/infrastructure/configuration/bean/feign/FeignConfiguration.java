package dev.ferv.restaurant_service.infrastructure.configuration.bean.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Retryer;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class FeignConfiguration {

    @Bean //intentos 
    Retryer retryer() {
        return new Retryer.Default(1000, 2000, 3); //  inicial - máximo - número de intentos
    }

}
