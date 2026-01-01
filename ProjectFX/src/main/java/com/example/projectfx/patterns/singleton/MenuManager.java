package com.example.projectfx.patterns.singleton;

import com.example.projectfx.model.MenuItem;
import com.example.projectfx.patterns.factory.MenuItemFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuManager {
    // only instance
    private static MenuManager instance;
    // Singleton + Factory
    private MenuItemFactory factoryItem;
    private List<MenuItem> menuItems;

    // Key Singleton rule
    //Prevents direct instantiation using new
    private MenuManager(MenuItemFactory factoryItem) {
        this.factoryItem = factoryItem;
        menuItems = new ArrayList<>();

        // إضافة عناصر مبدئية باستخدام الفاكتوري
        menuItems.add(factoryItem.createMenuItem("maincourse", "Pizza", 12.99));
        menuItems.add(factoryItem.createMenuItem("Appetizer", "Coke", 10.0));
        menuItems.add(factoryItem.createMenuItem("Dessert", "Pasta", 10.50));
    }

    // Singleton getInstance method
    public static synchronized MenuManager getInstance() {
        if (instance == null) {
            instance = new MenuManager(new MenuItemFactory());
        }
        return instance;
    }

    // ترجع نسخة غير قابلة للتعديل من قائمة الأصناف
    public List<MenuItem> getAllMenuItems() {
        return Collections.unmodifiableList(menuItems);
    }

    // إضافة صنف جديد
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    // حذف صنف حسب الاسم
    public void removeMenuItem(String itemName) {
        menuItems.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
    }

    // ترجع أسماء كل الأصناف
    public List<String> getMenuItemNames() {
        List<String> names = new ArrayList<>();
        for (MenuItem item : menuItems) {
            names.add(item.getName());
        }
        return names;
    }

    // ترجع سعر صنف معين (null لو مش موجود)
    public Double getMenuItemPrice(String itemName) {
        for (MenuItem item : menuItems) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item.getPrice();
            }
        }
        return null;
    }
}
