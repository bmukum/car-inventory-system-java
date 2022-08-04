module desktopapp.desktopapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens main to javafx.fxml;
    exports main;
    exports model;
    opens model to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
}