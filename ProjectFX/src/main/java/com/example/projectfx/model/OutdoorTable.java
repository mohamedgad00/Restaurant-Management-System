package com.example.projectfx.model;

/**
 * OutdoorTable - طاولة خارجية
 */
public class OutdoorTable extends Table {
    public OutdoorTable(int tableNumber, int capacity) {
        super(tableNumber, capacity);
        setType("Outdoor");
    }
}