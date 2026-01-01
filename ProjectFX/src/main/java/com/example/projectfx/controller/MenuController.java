package com.example.projectfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.projectfx.model.MenuItem;
import com.example.projectfx.patterns.factory.MenuItemFactory;
import com.example.projectfx.patterns.singleton.MenuManager;

public class MenuController {

    @FXML private TableView<MenuItem> menuTable;
    @FXML private ComboBox<String> typeFilter;
    @FXML private TextField searchField;

    @FXML private TextField nameField;
    @FXML private ComboBox<String> typeField;
    @FXML private TextField priceField;

    private ObservableList<MenuItem> menuItems;
    private MenuItemFactory menuFactory;
    private MenuManager menuManager;

    @FXML
    public void initialize() {
        menuFactory = new MenuItemFactory();
        menuManager = MenuManager.getInstance();

        menuItems = FXCollections.observableArrayList(menuManager.getAllMenuItems());

        // إعداد أعمدة الجدول وربطها بالخصائص
        TableColumn<MenuItem, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<MenuItem, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<MenuItem, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        menuTable.getColumns().setAll(nameCol, typeCol, priceCol);

        // إعداد فلتر النوع
        ObservableList<String> filterOptions = FXCollections.observableArrayList(
                "All", "Appetizer", "Main Course", "Dessert", "Beverage"
        );
        typeFilter.setItems(filterOptions);
        typeFilter.getSelectionModel().selectFirst();

        // إعداد خيارات الفورم (الإضافة)
        ObservableList<String> typeOptions = FXCollections.observableArrayList(
                "Appetizer", "Main Course", "Dessert", "Beverage"
        );
        typeField.setItems(typeOptions);
        typeField.getSelectionModel().selectFirst();

        menuTable.setItems(menuItems);

        System.out.println("MenuController initialized with MenuManager");
    }

    @FXML
    private void addNewItemFromForm() {
        String name = nameField.getText();
        String type = typeField.getValue();
        String priceText = priceField.getText();

        if (name == null || name.isEmpty() || type == null || priceText == null || priceText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all fields", ButtonType.OK);
            alert.setTitle("Validation Error");
            alert.showAndWait();
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Price must be a valid number", ButtonType.OK);
            alert.setTitle("Validation Error");
            alert.showAndWait();
            return;
        }

        MenuItem newItem = menuFactory.createMenuItem(type.toLowerCase().replace(" ", ""), name, price);
        menuManager.addMenuItem(newItem);
        menuItems.add(newItem);
        menuTable.refresh();

        // مسح الحقول بعد الإضافة
        nameField.clear();
        priceField.clear();
        typeField.getSelectionModel().selectFirst();

        System.out.println("✅ New menu item added from form: " + name);
    }

    @FXML
    private void searchItems() {
        String keyword = searchField.getText() == null ? "" : searchField.getText().toLowerCase();
        String selectedType = typeFilter.getValue();

        if (keyword.isEmpty() && (selectedType == null || selectedType.equals("All"))) {
            menuTable.setItems(menuItems);
            return;
        }

        ObservableList<MenuItem> filtered = FXCollections.observableArrayList();
        for (MenuItem item : menuItems) {
            boolean matchesKeyword = keyword.isEmpty() || item.getName().toLowerCase().contains(keyword);
            boolean matchesType = selectedType == null || selectedType.equals("All") || item.getCategory().equalsIgnoreCase(selectedType);

            if (matchesKeyword && matchesType) {
                filtered.add(item);
            }
        }

        menuTable.setItems(filtered);
    }
}
