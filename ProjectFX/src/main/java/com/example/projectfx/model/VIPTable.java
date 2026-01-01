package com.example.projectfx.model;

/**
 * VIPTable - طاولة VIP
 */
public class VIPTable extends Table {
    public VIPTable(int tableNumber, int capacity) {
        super(tableNumber, capacity);
        setType("VIP");
    }
}