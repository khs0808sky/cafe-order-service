package com.portfolio.cafeorderservice.order;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponse {

    private Long id;
    private String customerName;
    private int totalPrice;
    private OrderStatus status;
    private LocalDateTime orderedAt;
    private List<OrderItemResponse> items;

    public OrderResponse(
            Long id,
            String customerName,
            int totalPrice,
            OrderStatus status,
            LocalDateTime orderedAt,
            List<OrderItemResponse> items
    ) {
        this.id = id;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderedAt = orderedAt;
        this.items = items;
    }

    public static OrderResponse from(CafeOrder order) {
        List<OrderItemResponse> items = order.getOrderItems()
                .stream()
                .map(OrderItemResponse::from)
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getOrderedAt(),
                items
        );
    }

    @Getter
    public static class OrderItemResponse {

        private Long menuId;
        private String menuName;
        private int orderPrice;
        private int quantity;
        private int subtotalPrice;

        public OrderItemResponse(
                Long menuId,
                String menuName,
                int orderPrice,
                int quantity,
                int subtotalPrice
        ) {
            this.menuId = menuId;
            this.menuName = menuName;
            this.orderPrice = orderPrice;
            this.quantity = quantity;
            this.subtotalPrice = subtotalPrice;
        }

        public static OrderItemResponse from(OrderItem item) {
            return new OrderItemResponse(
                    item.getMenuId(),
                    item.getMenuName(),
                    item.getOrderPrice(),
                    item.getQuantity(),
                    item.getSubtotalPrice()
            );
        }
    }
}