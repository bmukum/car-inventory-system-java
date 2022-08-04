package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        primaryStage.setTitle("Inventory System");
        primaryStage.setScene(new Scene(root, 951, 617));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}