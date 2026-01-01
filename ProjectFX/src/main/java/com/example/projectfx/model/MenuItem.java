package com.example.projectfx.model;

/**
 * MenuItem - الفئة الأساسية لأصناف القائمة
 */
public class MenuItem {
    private String name;
    private double price;
    private String category; // Appetizer, Main Course, Dessert

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    // Setters
    public void setCategory(String category) { this.category = category; }

}