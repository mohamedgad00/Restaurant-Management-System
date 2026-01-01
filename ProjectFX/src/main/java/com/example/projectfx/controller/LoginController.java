package com.example.projectfx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.projectfx.patterns.proxy.DashboardProxy;
import com.example.projectfx.util.SessionManager;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void handleLogin() {

        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        errorLabel.setVisible(false);

        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password");
            return;
        }

        // Proxy = Authentication & Authorization
        DashboardProxy proxy = new DashboardProxy(username, password);

        if (proxy.loginSuccessful()) {

            SessionManager.getInstance().setCurrentUser(username);
            SessionManager.getInstance().setUserRole(proxy.getUserRole());

            System.out.println("✅ Login successful: " + username);

            loadDashboard(proxy.getUserRole());

        } else {
            showError("Invalid username or password");
        }
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    private void loadDashboard(String role) {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();


            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/projectfx/views/DashboardView.fxml")
            );
            Parent root = loader.load();
            Stage dashboardStage = new Stage();

            dashboardStage.setScene(new Scene(root, 1000, 700));
            dashboardStage.setTitle("Restaurant Dashboard - " + role.toUpperCase());
            dashboardStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showError("Error loading dashboard");
        }
    }
}
