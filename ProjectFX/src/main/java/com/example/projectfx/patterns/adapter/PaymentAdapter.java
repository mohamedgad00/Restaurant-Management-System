package com.example.projectfx.patterns.adapter;

import com.example.projectfx.patterns.singleton.PaymentSystem;

public class PaymentAdapter {

    private PaymentSystem paymentSystem;

    public PaymentAdapter() {
        // الحصول على النسخة الوحيدة من PaymentSystem (Singleton)
        paymentSystem = PaymentSystem.getInstance();
    }

    public boolean processPayment(
            String paymentType,
            String details,
            double amount) {

        String normalizedType = paymentType.toLowerCase()
                .replace(" ", "");
        return switch (normalizedType) {
            case "creditcard" -> {
                System.out.println("💳 Paying using CREDIT CARD");
                paymentSystem.payByCard(amount);
                yield true;
            }
            case "cash" -> {
                System.out.println("🔄 Adapting CASH payment to CARD system...");
                paymentSystem.payByCard( amount);
                yield true;
            }
            case "mobilewallet" -> {
                System.out.println("🔄 Adapting MOBILE WALLET to CARD system...");
                paymentSystem.payByCard( amount);
                yield true;
            }

            default -> {
                System.out.println("❌ Unsupported payment type: " + paymentType);
                yield false;
            }
        };
    }
}
