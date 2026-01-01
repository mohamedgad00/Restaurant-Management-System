package com.example.projectfx.patterns.singleton;

/**
 * PaymentSystem - Singleton Pattern لنظام المدفوعات
 * المطلوب: نظام مركزي لمعالجة جميع المدفوعات
 */
public class PaymentSystem {

    private static PaymentSystem instance;
    private double totalRevenue;

    private PaymentSystem() {
        totalRevenue = 0.0;
        System.out.println("✅ PaymentSystem Singleton created");
    }

    public static PaymentSystem getInstance() {
        if (instance == null) {
            instance = new PaymentSystem();
        }
        return instance;
    }
    public void payByCard( double amount) {
        totalRevenue += amount;
        System.out.println("💳 Legacy CARD payment processed: $" + amount);
    }
    public double getTotalRevenue() {
        return totalRevenue;
    }
    public void printPaymentReport() {
        System.out.println("📊 Payment System Report");
        System.out.println("Total Revenue: $" + totalRevenue);
    }
}