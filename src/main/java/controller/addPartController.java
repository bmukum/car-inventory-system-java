package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addPartController implements Initializable {
    public AnchorPane cancel;
    public AnchorPane pane;
    public RadioButton partInHouse;
    public RadioButton partOutSourced;
    public ToggleGroup tgroup;
    public Button save;
    public Label changeMe;
    public Button cancelLabel;
    public TextField idTF;
    public TextField nameTF;
    public TextField priceTF;
    public TextField minTF;
    public TextField maxTF;
    public TextField stockTF;
    public TextField machineID;
    public TextField machineIdTF;
    public Label changeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Add Parts");

    }

    public void saveButtonClick(ActionEvent actionEvent) throws IOException {
        int nextId = 0;
        try {
            ObservableList<Part> allParts = Inventory.getAllParts();
            nextId = allParts.get(allParts.size() - 1).getId() + 1;

        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        ObservableList<Part> newParts = FXCollections.observableArrayList(); //verify correctness

        int Id = nextId;
        String partName = nameTF.getText();

        try {
             int partStock = Integer.parseInt(stockTF.getText());
             double partPrice = Double.parseDouble(priceTF.getText());
             int partMin = Integer.parseInt(minTF.getText());
             int partMax = Integer.parseInt(maxTF.getText());
             if (partInHouse.isSelected()) {
                 int machineId = Integer.parseInt(machineIdTF.getText());
                 Part newPart = new InHouse(nextId, partName, partPrice, partStock, partMin, partMax, machineId);
                 Inventory.addPart(newPart);

             }
             else if (partOutSourced.isSelected()) {
                 String companyName = machineIdTF.getText();
                 Part newPart = new Outsourced(nextId, partName, partPrice, partStock, partMin, partMax, companyName);
                 Inventory.addPart(newPart);
             }

        }
        catch (NumberFormatException e){
            throw new RuntimeException(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1377, 683);
        stage.setTitle("Back to Main");
        stage.setScene(scene);
        stage.show();
    }

    public void cancelButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1377, 683);
        stage.setTitle("Back to Main");
        stage.setScene(scene);
        stage.show();
    }

    public void onPartInHouse(ActionEvent actionEvent) throws IOException {
        partInHouse.setSelected(true);
        changeLabel.setText("Machine ID");
    }

    public void onPartOutSourced(ActionEvent actionEvent) {
        partOutSourced.setSelected(true);
        changeLabel.setText("Company Name");
    }
}


