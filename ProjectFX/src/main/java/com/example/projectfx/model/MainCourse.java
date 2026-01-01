package com.example.projectfx.model;

/**
 * MainCourse - صنف الوجبة الرئيسية
 */
public class MainCourse extends MenuItem {
    public MainCourse(String name, double price) {
        super(name, price);
        setCategory("Main Course");
    }
}