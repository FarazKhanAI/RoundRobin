module org.app.roundrobin {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    exports org.app.roundrobin;
    exports org.app.roundrobin.model;
    exports org.app.roundrobin.controller;
    opens org.app.roundrobin to javafx.fxml;
    opens org.app.roundrobin.controller to javafx.fxml;
}
