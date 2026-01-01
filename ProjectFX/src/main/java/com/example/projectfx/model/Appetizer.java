package com.example.projectfx.model;

/**
 * Appetizer - صنف المقبلات
 */
public class Appetizer extends MenuItem {
    public Appetizer(String name, double price) {
        super(name, price);
        setCategory("Appetizer");
    }
}