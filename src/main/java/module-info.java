module desktopapp.desktopapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens desktopapp.desktopapp to javafx.fxml;
    exports desktopapp.desktopapp;
}