package com.portfolio.cafeorderservice.order;

import com.portfolio.cafeorderservice.menu.Menu;
import com.portfolio.cafeorderservice.menu.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final CafeOrderRepository cafeOrderRepository;
    private final MenuRepository menuRepository;

    public OrderService(
            CafeOrderRepository cafeOrderRepository,
            MenuRepository menuRepository
    ) {
        this.cafeOrderRepository = cafeOrderRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    public OrderResponse createOrder(OrderCreateRequest request) {
        CafeOrder order = new CafeOrder(request.getCustomerName());

        for (OrderCreateRequest.OrderMenuRequest itemRequest : request.getItems()) {
            Menu menu = menuRepository.findById(itemRequest.getMenuId())
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 메뉴입니다."));

            if (!menu.isActive()) {
                throw new RuntimeException("판매 중지된 메뉴는 주문할 수 없습니다.");
            }

            OrderItem orderItem = new OrderItem(
                    menu.getId(),
                    menu.getName(),
                    menu.getPrice(),
                    itemRequest.getQuantity()
            );

            order.addOrderItem(orderItem);
        }

        CafeOrder savedOrder = cafeOrderRepository.save(order);

        return OrderResponse.from(savedOrder);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getOrders() {
        return cafeOrderRepository.findAll()
                .stream()
                .map(OrderResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long id) {
        CafeOrder order = cafeOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 주문입니다."));

        return OrderResponse.from(order);
    }

    @Transactional
    public OrderResponse updateOrderStatus(Long id, OrderStatusUpdateRequest request) {
        CafeOrder order = cafeOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 주문입니다."));

        order.changeStatus(request.getStatus());

        return OrderResponse.from(order);
    }

    @Transactional(readOnly = true)
    public SalesResponse getDailySales(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        List<CafeOrder> orders = cafeOrderRepository.findByOrderedAtBetweenAndStatusNot(
                start,
                end,
                OrderStatus.CANCELED
        );

        int totalSales = orders.stream()
                .mapToInt(CafeOrder::getTotalPrice)
                .sum();

        return new SalesResponse(date, totalSales, orders.size());
    }
}