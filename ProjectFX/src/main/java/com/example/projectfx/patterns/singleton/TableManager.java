package com.example.projectfx.patterns.singleton;

import com.example.projectfx.model.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TableManager {
    private static TableManager instance;
    // Automatically updates UI when data changes
    private ObservableList<Table> tables;

    private TableManager() {
        tables = FXCollections.observableArrayList();
        // ممكن تضيف طاولات افتراضية هنا لو تحب
    }

    public static synchronized TableManager getInstance() {
        if (instance == null) {
            instance = new TableManager();
        }
        return instance;
    }

    public ObservableList<Table> getTablesObservable() {
        return tables;
    }

    public void addTable(Table table) {
        tables.add(table);
    }
}
