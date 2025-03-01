package dev.ferv.restaurant_service.domain.port.in.order;

public interface ICancelOrderUseCase {

    void cancelOrder(Long orderId);

}
