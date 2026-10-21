module com.example.rutaverdequindio {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rutaverdequindio to javafx.fxml;
    opens com.example.rutaverdequindio.controller to javafx.fxml;
    exports com.example.rutaverdequindio;
}