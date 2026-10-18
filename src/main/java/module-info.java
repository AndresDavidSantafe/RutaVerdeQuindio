module com.example.rutaverdequindio {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rutaverdequindio to javafx.fxml;
    exports com.example.rutaverdequindio;
}