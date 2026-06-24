package com.portfolio.cafeorderservice.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderStatusUpdateRequest {

    @NotNull(message = "주문 상태는 필수입니다.")
    private OrderStatus status;
}