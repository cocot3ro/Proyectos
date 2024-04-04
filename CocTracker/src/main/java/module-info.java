module org.example.coctracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.coctracker to javafx.fxml;
    exports org.example.coctracker;
}