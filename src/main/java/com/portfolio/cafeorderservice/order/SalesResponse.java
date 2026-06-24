package com.portfolio.cafeorderservice.order;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SalesResponse {

    private LocalDate date;
    private int totalSales;
    private int orderCount;

    public SalesResponse(LocalDate date, int totalSales, int orderCount) {
        this.date = date;
        this.totalSales = totalSales;
        this.orderCount = orderCount;
    }
}