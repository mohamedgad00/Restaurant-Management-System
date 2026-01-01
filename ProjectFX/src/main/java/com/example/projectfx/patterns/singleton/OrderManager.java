package com.example.projectfx.patterns.singleton;

import com.example.projectfx.model.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;
    private List<Order> orders;

    private OrderManager() {
        orders = new ArrayList<>();
    }

    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public void updateOrderStatus(String orderId, String newStatus) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                order.setStatus(newStatus);
                break;
            }
        }
    }
}
