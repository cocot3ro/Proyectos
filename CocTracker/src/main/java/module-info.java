module com.cocot3ro.coctracker.coctracker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires static lombok;
    requires jakarta.persistence;

    opens com.cocot3ro.coctracker.coctracker to javafx.fxml;
    exports com.cocot3ro.coctracker.coctracker.ui.view;
    opens com.cocot3ro.coctracker.coctracker.ui.view to javafx.fxml;
    exports com.cocot3ro.coctracker.coctracker.ui.controller;
    opens com.cocot3ro.coctracker.coctracker.ui.controller to javafx.fxml;
}