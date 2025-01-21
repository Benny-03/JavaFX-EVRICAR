module com.example.evricar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.evricar to javafx.fxml;
    exports com.example.evricar;
}