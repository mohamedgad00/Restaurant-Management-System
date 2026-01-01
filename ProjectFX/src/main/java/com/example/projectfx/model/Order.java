package com.example.projectfx.model;

import java.util.ArrayList;
import java.util.List;


public class Order {
    private String orderId;
    private String customerName;
    private String tableNumber;
    private List<String> items;
    private double totalAmount;
    private String status; // Pending, Preparing, Ready, Completed

    public Order() {
        this.items = new ArrayList<>();
        this.status = "Pending";
        this.orderId = generateOrderId();
    }

    private String generateOrderId() {
        return "ORD" + System.currentTimeMillis();
    }

    // Getters
    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getTableNumber() { return tableNumber; }
    public List<String> getItems() { return new ArrayList<>(items); }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }

    // Setters
    public void setCustomerName(String name) { this.customerName = name; }
    public void setTableNumber(String table) { this.tableNumber = table; }
    public void setStatus(String status) { this.status = status; }

    public void addItem(String item, double price) {
        items.add(item);
        totalAmount += price;
    }

    @Override
    public String toString() {
        return "Order #" + orderId + " - " + customerName + " - $" + totalAmount;
    }
}