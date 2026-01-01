package com.example.projectfx.patterns.builder;

import com.example.projectfx.model.Order;

/**
 * Builder Pattern - Order Builder
 * إضافي: بناء كائنات الطلبات بطريقة مرنة
 */
public class OrderBuilder {
    private Order order;

    public OrderBuilder() {
        this.order = new Order();
    }
    public OrderBuilder setCustomer(String customerName) {
        order.setCustomerName(customerName);
        return this;
    }
    public OrderBuilder setTable(String tableNumber) {
        order.setTableNumber(tableNumber);
        return this;
    }
    public OrderBuilder addItem(String itemName, double price) {
        order.addItem(itemName, price);
        return this;
    }
    public OrderBuilder setStatus(String status) {
        order.setStatus(status);
        return this;
    }
    public Order build() {
        System.out.println("🏗️ Order built successfully: " + order.getOrderId());
        return order;
    }

    public static Order createQuickOrder(String customer, String table) {
        return new OrderBuilder()
                .setCustomer(customer)
                .setTable(table)
                .addItem("Pizza", 12.99)
                .addItem("Coke", 2.99)
                .setStatus("Preparing")
                .build();
    }
}