package com.portfolio.cafeorderservice.order;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long menuId;

    private String menuName;

    private int orderPrice;

    private int quantity;

    private int subtotalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private CafeOrder cafeOrder;

    public OrderItem(Long menuId, String menuName, int orderPrice, int quantity) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
        this.subtotalPrice = orderPrice * quantity;
    }

    public void setCafeOrder(CafeOrder cafeOrder) {
        this.cafeOrder = cafeOrder;
    }
}