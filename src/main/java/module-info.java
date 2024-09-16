module com.example.homework_3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.homework_3 to javafx.fxml;
    exports com.example.homework_3;
}