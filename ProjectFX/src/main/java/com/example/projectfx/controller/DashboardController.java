package com.example.projectfx.controller;

import com.example.projectfx.patterns.proxy.DashboardProxy;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.example.projectfx.util.SessionManager;

public class DashboardController {

    @FXML private StackPane contentArea;
    @FXML private Label welcomeLabel;
    @FXML private Label statusLabel;
    private DashboardProxy dashboardProxy;

    @FXML
    public void initialize() {

        String username = SessionManager.getInstance().getCurrentUser();
        String role = SessionManager.getInstance().getUserRole();


        // ===== Welcome Label =====
        if (welcomeLabel != null) {
            welcomeLabel.setText("Welcome " +
                    (username != null ? username : "Guest") + " 👋");
        }

        // ===== Status / Role Hint =====
        if (statusLabel != null) {
            switch (role) {
                case "admin":
                    statusLabel.setText("👑 Logged in as ADMIN – Full Access");
                    break;

                case "manager":
                    statusLabel.setText("📊 Logged in as MANAGER – Reports & Staff");
                    break;

                case "staff":
                    statusLabel.setText("👤 Logged in as STAFF – Orders Only");
                    break;

                default:
                    statusLabel.setText("Guest mode");
            }
        } else {
            System.err.println("⚠️ Warning: statusLabel is null in DashboardController");
        }

        // ===== Load default view =====
        showOrders();
    }


    @FXML
    private void showOrders() {
        loadView("OrderView.fxml");
        if (statusLabel != null) {
            statusLabel.setText("Viewing: Orders");
        }
    }

    @FXML
    private void showMenu() {
        loadView("MenuView.fxml");
        if (statusLabel != null) {
            statusLabel.setText("Viewing: Menu");
        }
    }

    @FXML
    private void showTables() {
        loadView("TableView.fxml");
        if (statusLabel != null) {
            statusLabel.setText("Viewing: Tables");
        }
    }

    @FXML
    private void logout() {
        try {
            SessionManager.getInstance().clearSession();

            Stage stage = (Stage) contentArea.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(
                    getClass().getResource("/com/example/projectfx/views/LoginView.fxml")
            );

            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root, 800, 500));
            loginStage.setTitle("Restaurant Management System - Login");
            loginStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadView(String fxmlFile) {
        try {
            Parent view = FXMLLoader.load(
                    getClass().getResource("/com/example/projectfx/views/" + fxmlFile)
            );
            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);
        } catch (Exception e) {
            e.printStackTrace();
            if (statusLabel != null) {
                statusLabel.setText("Error loading view");
            }
        }
    }
}