package com.example.projectfx.patterns.factory;

import com.example.projectfx.model.*;

public class MenuItemFactory {

    /**
     * Factory method لإنشاء أصناف القائمة
     * @param type نوع الصنف (appetizer, maincourse, dessert)
     * @param name اسم الصنف
     * @param price سعر الصنف
     * @return MenuItem object
     */
    public MenuItem createMenuItem(String type, String name, double price) {
        switch (type.toLowerCase()) {
            case "appetizer":
                return  createAppetizer(name, price);
            case "maincourse":
                return  createMainCourse(name, price);
            case "dessert":
                return createDessert(name, price);
            default:
                throw new IllegalArgumentException("❌ Unknown menu item type: " + type);
        }
    }

    /**
     * إنشاء مقبلات
     */
    public MenuItem createAppetizer(String name, double price) {
        return new Appetizer(name, price);
    }

    /**
     * إنشاء وجبة رئيسية
     */
    public MenuItem createMainCourse(String name, double price) {
        return new MainCourse(name, price);
    }

    /**
     * إنشاء حلوى
     */
    public MenuItem createDessert(String name, double price) {
        return new Dessert(name, price);
    }
}