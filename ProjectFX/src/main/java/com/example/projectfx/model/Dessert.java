package com.example.projectfx.model;

/**
 * Dessert - صنف الحلويات
 */
public class Dessert extends MenuItem {
    public Dessert(String name, double price) {
        super(name, price);
        setCategory("Dessert");
    }
}