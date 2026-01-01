package com.example.projectfx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import com.example.projectfx.model.Table;
import com.example.projectfx.patterns.factory.TableFactory;
import com.example.projectfx.patterns.singleton.TableManager;

public class TableController {

    @FXML private TableView<Table> tablesTable;
    @FXML private TableColumn<Table, Integer> tableNumberCol;
    @FXML private TableColumn<Table, String> typeCol;
    @FXML private TableColumn<Table, Integer> capacityCol;
    @FXML private TableColumn<Table, String> statusCol;

    @FXML private ComboBox<String> statusFilter;

    @FXML private TextField tableNumberField;
    @FXML private ComboBox<String> typeField;
    @FXML private TextField capacityField;
    @FXML private ComboBox<String> statusField;

    private TableManager tableManager;
    private TableFactory tableFactory;

    @FXML
    public void initialize() {
        tableManager = TableManager.getInstance();
        tableFactory = new TableFactory();

        // إعداد أعمدة الجدول وربطها بخصائص Table
        tableNumberCol.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getTableNumber()).asObject());

        typeCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getType()));

        capacityCol.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getCapacity()).asObject());

        statusCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStatus()));

        // إعداد ComboBoxes
        ObservableList<String> typeOptions = FXCollections.observableArrayList("Regular", "VIP", "Outdoor");
        typeField.setItems(typeOptions);
        typeField.getSelectionModel().selectFirst();

        ObservableList<String> statusOptions = FXCollections.observableArrayList("Available", "Occupied", "Reserved");
        statusField.setItems(statusOptions);
        statusField.getSelectionModel().selectFirst();

        statusFilter.setItems(FXCollections.observableArrayList("All", "Available", "Occupied", "Reserved"));
        statusFilter.getSelectionModel().selectFirst();

        // تحميل بيانات الطاولات في الجدول
        tablesTable.setItems(tableManager.getTablesObservable());

        // أضف طاولات تجريبية لو القائمة فارغة
        if (tableManager.getTablesObservable().isEmpty()) {
            addSampleTables();
        }

        System.out.println("TableController initialized with TableManager");


        // استماع لتغيير الفلتر
        statusFilter.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> filterTables());
    }

    private void addSampleTables() {
        Table t1 = tableFactory.createTable("regular", 1, 4);
        t1.setStatus("Available");
        tableManager.addTable(t1);

        Table t2 = tableFactory.createTable("vip", 2, 6);
        t2.setStatus("Occupied");
        tableManager.addTable(t2);

        Table t3 = tableFactory.createTable("outdoor", 3, 2);
        t3.setStatus("Reserved");
        tableManager.addTable(t3);
    }

    @FXML
    private void addNewTableFromForm() {
        String tableNumStr = tableNumberField.getText();
        String capacityStr = capacityField.getText();
        String type = typeField.getValue();
        String status = statusField.getValue();

        if (tableNumStr.isEmpty() || capacityStr.isEmpty() || type == null || status == null) {
            showAlert("Validation Error", "Please fill all fields");
            return;
        }

        int tableNumber;
        int capacity;

        try {
            tableNumber = Integer.parseInt(tableNumStr);
            capacity = Integer.parseInt(capacityStr);
            if (tableNumber <= 0 || capacity <= 0) {
                showAlert("Validation Error", "Table number and capacity must be positive numbers");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Validation Error", "Table number and capacity must be valid integers");
            return;
        }

        Table newTable = tableFactory.createTable(type.toLowerCase(), tableNumber, capacity);
        newTable.setStatus(status);

        tableManager.addTable(newTable);
        clearForm();
        System.out.println("✅ Added new table: " + newTable);
    }

    @FXML
    private void filterTables() {
        String filter = statusFilter.getValue();

        if (filter == null || filter.equals("All")) {
            tablesTable.setItems(tableManager.getTablesObservable());
            return;
        }

        ObservableList<Table> filtered = FXCollections.observableArrayList();
        for (Table t : tableManager.getTablesObservable()) {
            if (t.getStatus().equalsIgnoreCase(filter)) {
                filtered.add(t);
            }
        }
        tablesTable.setItems(filtered);
    }

    private void clearForm() {
        tableNumberField.clear();
        capacityField.clear();
        typeField.getSelectionModel().selectFirst();
        statusField.getSelectionModel().selectFirst();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
