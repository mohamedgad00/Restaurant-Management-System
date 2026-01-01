package com.example.projectfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.println("🚀 Starting Restaurant Management System...");


            URL fxmlUrl = getClass().getResource("/com/example/projectfx/views/LoginView.fxml");

            if (fxmlUrl == null) {
                System.err.println("❌ ERROR: LoginView.fxml NOT FOUND!");
                return;
            }

            Parent root = FXMLLoader.load(fxmlUrl);
            Scene scene = new Scene(root, 800, 500);

            primaryStage.setTitle("Restaurant Management System");
            primaryStage.setScene(scene);
            primaryStage.show();

            System.out.println("✅ Application started!");

        } catch (Exception e) {
            System.err.println("❌ Error starting application:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}