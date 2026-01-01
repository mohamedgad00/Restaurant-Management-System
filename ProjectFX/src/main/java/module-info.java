module com.example.projectfx {
    requires javafx.controls;
    requires javafx.fxml;

    // فتح كل الـ packages
    opens com.example.projectfx to javafx.fxml;
    opens com.example.projectfx.controller to javafx.fxml;
    opens com.example.projectfx.model to javafx.base;


    exports com.example.projectfx;
}