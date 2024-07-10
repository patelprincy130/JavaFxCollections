module com.example.collection {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.collection to javafx.fxml;
    exports com.example.collection;
}