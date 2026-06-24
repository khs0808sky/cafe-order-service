package com.portfolio.cafeorderservice.order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class CafeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.ORDERED;

    private LocalDateTime orderedAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "cafeOrder", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public CafeOrder(String customerName) {
        this.customerName = customerName;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setCafeOrder(this);
        totalPrice += orderItem.getSubtotalPrice();
    }

    public void changeStatus(OrderStatus newStatus) {
        if (this.status == OrderStatus.CANCELED) {
            throw new RuntimeException("취소된 주문은 상태를 변경할 수 없습니다.");
        }

        if (this.status == OrderStatus.COMPLETED) {
            throw new RuntimeException("완료된 주문은 상태를 변경할 수 없습니다.");
        }

        this.status = newStatus;
    }

    @PrePersist
    public void prePersist() {
        orderedAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}