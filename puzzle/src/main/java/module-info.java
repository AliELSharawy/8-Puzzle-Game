module com.example.puzzle {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.puzzle to javafx.fxml;
    exports com.example.puzzle;
}