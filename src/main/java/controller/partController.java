package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class partController implements Initializable {
    public AnchorPane cancel;
    public AnchorPane pane;
    public RadioButton partInHouse;
    public RadioButton partOutSourced;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Add Parts");

    }

    public void saveButtonClick(ActionEvent actionEvent) {
        System.out.print("Saved!");
    }

    public void InHouseClick(ActionEvent actionEvent) {
    }

    public void outSourcedClick(ActionEvent actionEvent) {
    }

    public void cancelButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 951, 617);
        stage.setTitle("Back to Main");
        stage.setScene(scene);
        stage.show();
    }

    public void onPartInHouse(ActionEvent actionEvent) {
    }

    public void onPartOutSourced(ActionEvent actionEvent) {
    }
}


