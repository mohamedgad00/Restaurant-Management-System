package com.example.projectfx.model;

import javafx.beans.property.*;

public class Table {
    private IntegerProperty tableNumber;
    private IntegerProperty capacity;
    private StringProperty type;
    private StringProperty status;

    public Table(int tableNumber, int capacity) {
        this.tableNumber = new SimpleIntegerProperty(tableNumber);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.type = new SimpleStringProperty("Regular");
        this.status = new SimpleStringProperty("Available");
    }

    // Getters and Setters for properties

    public int getTableNumber() {
        return tableNumber.get();
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber.set(tableNumber);
    }

    public IntegerProperty tableNumberProperty() {
        return tableNumber;
    }

    public int getCapacity() {
        return capacity.get();
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }

    @Override
    public String toString() {
        return "Table #" + getTableNumber() + ", Type: " + getType() + ", Capacity: " + getCapacity() + ", Status: " + getStatus();
    }
}
