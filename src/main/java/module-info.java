module com.example.psi3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.psi3 to javafx.fxml;
    exports com.example.psi3;
}