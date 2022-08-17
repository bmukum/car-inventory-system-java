package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import model.Part;
import model.Product;

import java.io.IOException;

/**
 * Main class.
 */

/**
 *
 * @author Brandon Mukum
 *
 * FUTURE ENHANCEMENTS
 * The program would be better if the following are implemented:
 * - Having the app connected to a database where parts and products can be stored.
 * - Reduce the changes of typing in the wrong part name implementing a method to auto-complete the name when the user starts typing.
 * - Add a method/option plus confirmation for the product's list of associated parts be deleted when the product has to deleted.
 * This would make it easy to delete products, instead of having to modify that product and removing the parts, before
 * returning to the main screen to delete the product.
 */

public class main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        primaryStage.setTitle("Inventory System");
        primaryStage.setScene(new Scene(root, 1377, 683));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}


