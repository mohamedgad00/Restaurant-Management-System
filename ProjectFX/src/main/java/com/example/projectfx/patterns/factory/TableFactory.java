package com.example.projectfx.patterns.factory;

import com.example.projectfx.model.*;

/**
 * Factory Pattern - Table Factory
 * المطلوب: إنشاء أنواع مختلفة من الطاولات
 */
public class TableFactory {

    /**
     * Factory method لإنشاء طاولات
     * @param type نوع الطاولة (regular, vip, outdoor)
     * @param tableNumber رقم الطاولة
     * @param capacity سعة الطاولة
     * @return Table object
     */
    public Table createTable(String type, int tableNumber, int capacity) {
        switch (type.toLowerCase()) {
            case "regular":
                return new RegularTable(tableNumber, capacity);
            case "vip":
                return new VIPTable(tableNumber, capacity);
            case "outdoor":
                return new OutdoorTable(tableNumber, capacity);
            default:
                throw new IllegalArgumentException("❌ Unknown table type: " + type);
        }
    }

    /**
     * إنشاء طاولة عادية
     */
    public Table createRegularTable(int tableNumber, int capacity) {
        return new RegularTable(tableNumber, capacity);
    }

    /**
     * إنشاء طاولة VIP
     */
    public Table createVipTable(int tableNumber, int capacity) {
        return new VIPTable(tableNumber, capacity);
    }

    /**
     * إنشاء طاولة خارجية
     */
    public Table createOutdoorTable(int tableNumber, int capacity) {
        return new OutdoorTable(tableNumber, capacity);
    }
}