package com.example.projectfx.model;

/**
 * Payment - نموذج الدفع
 */
public class Payment {
    private String paymentId;
    private String orderId;
    private double amount;
    private String paymentMethod; // Cash, Credit Card, Mobile
    private String status; // Pending, Completed, Failed

    public Payment(String orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
        this.paymentId = "PAY" + System.currentTimeMillis();
        this.status = "Pending";
    }

    // Getters
    public String getPaymentId() { return paymentId; }
    public String getOrderId() { return orderId; }
    public double getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getStatus() { return status; }

    // Setters
    public void setPaymentMethod(String method) { this.paymentMethod = method; }
    public void setStatus(String status) { this.status = status; }

    public void completePayment() {
        this.status = "Completed";
    }
}