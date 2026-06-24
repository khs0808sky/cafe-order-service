package com.portfolio.cafeorderservice.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateRequest {

    @NotBlank(message = "고객 이름은 필수입니다.")
    private String customerName;

    @NotEmpty(message = "주문 메뉴는 1개 이상이어야 합니다.")
    @Valid
    private List<OrderMenuRequest> items;

    @Getter
    public static class OrderMenuRequest {

        @NotNull(message = "메뉴 ID는 필수입니다.")
        private Long menuId;

        @Min(value = 1, message = "수량은 1개 이상이어야 합니다.")
        private int quantity;
    }
}