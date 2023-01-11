module com.example.demo {
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;


    opens com.example.safari to javafx.fxml;
    exports com.example.safari;
}