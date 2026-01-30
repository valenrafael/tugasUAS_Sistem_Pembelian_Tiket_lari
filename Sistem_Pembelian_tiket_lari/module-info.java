module com.example.uas1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.uas1 to javafx.fxml;
    exports com.example.uas1;
}