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

/**
 * Controller class for the add part menu.
 */

/**
 *
 * @author Brandon Mukum
 */

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

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Add Parts");

    }

    private int nextId = 0;

    /**RUNTIME ERROR: The call to get the size of the allParts list failed for an empty list
     * because the length was zero and the method had a statement to substract 1 from the length which made it -1.
     * @return the nextId to assign to the part
     */
    private int getNextId() {
        try {
            ObservableList<Part> allParts = Inventory.getAllParts();
            nextId = allParts.get(allParts.size() - 1).getId() + 1;

        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        return nextId;
    }

    /**RUNTIME ERROR Initially, I was unable to convert the strings from min and max text fields to integers.
     * I had to fix that with the Integer.parsInt function.
     * Method that saves all the changes to inventory when the save button is clicked.
     */
    public void saveButtonClick(ActionEvent actionEvent) throws IOException {
        int id = getNextId();
        String partName = nameTF.getText();

        int partMax = 0;
        int partMin = 0;
        int partStock = 0;
        try {
            partStock = Integer.parseInt(stockTF.getText());
            double partPrice = Double.parseDouble(priceTF.getText());
            partMin = Integer.parseInt(minTF.getText());
            partMax = Integer.parseInt(maxTF.getText());

            if (partMax < partMin) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Maximum value cannot be less than minimum");
                alert.showAndWait();
                return;
            }

            if (partStock > partMax || partStock < partMin) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Stock value has to be in-between minimum and maximum");
                alert.showAndWait();
                return;
            }
            if (partInHouse.isSelected()) {
                int machineId = Integer.parseInt(machineIdTF.getText());
                Part newPart = new InHouse(nextId, partName, partPrice, partStock, partMin, partMax, machineId);

                Inventory.addPart(newPart);

            } else if (partOutSourced.isSelected()) {
                String companyName = machineIdTF.getText();
                Part newPart = new Outsourced(nextId, partName, partPrice, partStock, partMin, partMax, companyName);
                Inventory.addPart(newPart);
            }
            Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1377, 683);
            stage.setTitle("Inventory System");
            stage.setScene(scene);
            stage.show();

        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Type errors found. Please ensure you have entered values into all fields. Only numbers are allowed in the Inv, Price, Min and Max fields.");
            alert.showAndWait();
        }
    }

    /**
     * This method cancels all working changes and goes back to the main screen.
     */
    public void cancelButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1377, 683);
        stage.setTitle("Inventory System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method handles sets fields when the InHouse button is clicked.
     */
    public void onPartInHouse(ActionEvent actionEvent) throws IOException {
        partInHouse.setSelected(true);
        changeLabel.setText("Machine ID");
    }

    /**
     * This method sets fields when the outsourced button is clicked.
     */
    public void onPartOutSourced(ActionEvent actionEvent) {
        partOutSourced.setSelected(true);
        changeLabel.setText("Company Name");
    }
}


