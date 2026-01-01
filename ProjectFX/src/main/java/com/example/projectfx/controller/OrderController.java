package com.example.projectfx.controller;

import com.example.projectfx.model.Table;
import com.example.projectfx.patterns.adapter.PaymentAdapter;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.projectfx.model.Order;
import com.example.projectfx.patterns.singleton.OrderManager;
import com.example.projectfx.patterns.singleton.PaymentSystem;
import com.example.projectfx.patterns.singleton.MenuManager;
import com.example.projectfx.patterns.singleton.TableManager;
import com.example.projectfx.patterns.builder.OrderBuilder;

import java.util.List;

public class OrderController {

    @FXML private TableView<Order> ordersTable;
    @FXML private TableColumn<Order, String> orderIdCol;
    @FXML private TableColumn<Order, String> customerCol;
    @FXML private TableColumn<Order, String> tableCol;
    @FXML private TableColumn<Order, String> statusCol;
    @FXML private TableColumn<Order, Double> totalAmountCol;

    @FXML private TextField customerField;
    @FXML private ComboBox<String> tableComboBox;
    @FXML private ComboBox<String> itemComboBox;
    @FXML private TextField priceField;
    @FXML private ListView<String> orderItemsList;
    @FXML private Label statusLabel;
    @FXML private ComboBox<String> statusCombo;
    @FXML private ComboBox<String> paymentMethodCombo;


    private OrderManager orderManager;
    private PaymentSystem paymentSystem;
    private MenuManager menuManager;
    private TableManager tableManager;
    private ObservableList<Order> orders;
    private PaymentAdapter paymentAdapter = new PaymentAdapter();

    @FXML
    public void initialize() {
        orderManager = OrderManager.getInstance();
        paymentSystem = PaymentSystem.getInstance();
        menuManager = MenuManager.getInstance();
        tableManager = TableManager.getInstance();

        orderIdCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getOrderId()));
        customerCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCustomerName()));
        tableCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTableNumber()));
        statusCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getStatus()));
        totalAmountCol.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getTotalAmount()).asObject());

        statusCombo.setItems(FXCollections.observableArrayList(
                "Pending", "Preparing", "Ready", "Completed", "Cancelled", "Paid"
        ));
        statusCombo.getSelectionModel().selectFirst();

        paymentMethodCombo.setItems(FXCollections.observableArrayList(
                "Credit Card","Cash","Mobile Wallet"
        ));
        paymentMethodCombo.getSelectionModel().selectFirst();
        loadTables();
        loadMenuItems();

        // تحديث السعر تلقائي عند اختيار صنف
        itemComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Double price = menuManager.getMenuItemPrice(newVal);  // هنا غيرت السطر
                if (price != null) {
                    priceField.setText(String.format("%.2f", price));
                } else {
                    priceField.clear();
                }
            } else {
                priceField.clear();
            }
        });

        loadOrders();
        statusLabel.setText("Order Management - Ready");
    }

    private void loadTables() {
        ObservableList<Table> tables = tableManager.getTablesObservable();

        ObservableList<String> tablesObservable = FXCollections.observableArrayList();
        for (Table table : tables) {
            tablesObservable.add("Table " + table.getTableNumber()); // أو table.getTableName() لو موجود
        }

        tableComboBox.setItems(tablesObservable);
        if (!tablesObservable.isEmpty()) {
            tableComboBox.getSelectionModel().selectFirst();
        }
    }


    private void loadMenuItems() {
        List<String> items = menuManager.getMenuItemNames();
        ObservableList<String> itemsObservable = FXCollections.observableArrayList(items);
        itemComboBox.setItems(itemsObservable);
        if (!itemsObservable.isEmpty()) itemComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleAddItem() {
        String selectedItem = itemComboBox.getValue();
        String priceText = priceField.getText();

        if (selectedItem == null || selectedItem.isEmpty() || priceText == null || priceText.isEmpty()) {
            showError("Please select an item first.");
            return;
        }

        String itemEntry = selectedItem + ":" + priceText;
        orderItemsList.getItems().add(itemEntry);
    }

    @FXML
    private void handleCreateOrder() {
        try {
            String customer = customerField.getText().trim();
            String table = tableComboBox.getValue();
            ObservableList<String> items = orderItemsList.getItems();

            if (customer.isEmpty() || table == null || table.isEmpty()) {
                showError("Please enter customer name and select a table");
                return;
            }

            if (items.isEmpty()) {
                showError("Please add at least one item to the order");
                return;
            }

            OrderBuilder builder = new OrderBuilder()
                    .setCustomer(customer)
                    .setTable(table);

            for (String itemEntry : items) {
                String[] parts = itemEntry.split(":");
                if (parts.length == 2) {
                    String itemName = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    builder.addItem(itemName, price);
                }
            }

            Order newOrder = builder.build();
            orderManager.addOrder(newOrder);

            loadOrders();
            clearForm();

            statusLabel.setText("✅ Order created: " + newOrder.getOrderId());

        } catch (NumberFormatException e) {
            showError("Invalid price format for items");
        } catch (Exception e) {
            showError("Error creating order: " + e.getMessage());
        }
    }

    @FXML
    private void handleProcessPayment() {
        Order selected = ordersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String paymentType = paymentMethodCombo.getValue();
            String details = selected.getOrderId();

            boolean IsSuccess = paymentAdapter.processPayment(paymentType, details, selected.getTotalAmount());

            if (IsSuccess) {
                selected.setStatus("Paid");
                ordersTable.refresh();
                statusLabel.setText("✅ Payment processed for Order: " + selected.getOrderId());
            } else {
                showError("Unsupported payment method.");
            }

        } else {
            showError("Please select an order to process payment");
        }
    }

    @FXML
    private void handleUpdateStatus() {
        Order selected = ordersTable.getSelectionModel().getSelectedItem();
        String newStatus = statusCombo.getValue();

        if (selected != null && newStatus != null) {
            orderManager.updateOrderStatus(selected.getOrderId(), newStatus);
            ordersTable.refresh();
            loadOrders();
            statusLabel.setText("✅ Order " + selected.getOrderId() + " status updated to: " + newStatus);
        } else {
            showError("Please select an order and choose a status");
        }
    }

    @FXML
    private void handleDeleteOrder() {
        Order selected = ordersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            orderManager.removeOrder(selected);
            loadOrders();
            statusLabel.setText("❌ Order removed: " + selected.getOrderId());
        } else {
            showError("Please select an order to delete");
        }
    }

    @FXML
    private void handleRefresh() {
        loadOrders();
        clearForm();
        statusLabel.setText("Orders refreshed");
    }

    private void loadOrders() {
        List<Order> allOrders = orderManager.getAllOrders();
        orders = FXCollections.observableArrayList(allOrders);
        ordersTable.setItems(orders);
    }

    private void clearForm() {
        customerField.clear();
        tableComboBox.getSelectionModel().clearSelection();
        itemComboBox.getSelectionModel().clearSelection();
        priceField.clear();
        orderItemsList.getItems().clear();
        customerField.requestFocus();
    }

    private void showError(String message) {
        statusLabel.setText("❌ " + message);
    }
}
