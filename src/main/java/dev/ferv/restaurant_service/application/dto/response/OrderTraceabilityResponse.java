package dev.ferv.restaurant_service.application.dto.response;

import java.util.List;

import dev.ferv.restaurant_service.domain.model.client.StateLog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderTraceabilityResponse {

    private Long orderId;
    private List<StateLog> states;
    private String duration;
}
