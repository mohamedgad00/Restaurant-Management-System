package com.example.projectfx.model;

/**
 * RegularTable - طاولة عادية
 */
public class RegularTable extends Table {
    public RegularTable(int tableNumber, int capacity) {
        super(tableNumber, capacity);
        setType("Regular");
    }
}