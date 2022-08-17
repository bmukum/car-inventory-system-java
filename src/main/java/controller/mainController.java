package controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static model.Inventory.lookUpPart;
import static model.Inventory.lookUpProduct;

/**
 * Main controller file that handles operations on the program's main screen.
 */

/**
 *
 * @author Brandon Mukum
 */

public class mainController implements Initializable {
    public TableView partTable;
    public TableColumn partIdCol;
    public TableColumn partPriceCol;
    public TableView productTable;
    public TableColumn partNameCol;
    public Button addPart;
    public Button addProduct;
    public TableColumn productIdCol;
    public TableColumn productNameCol;
    public TableColumn productPriceCol;
    public TableColumn productStockCol;
    public TableColumn productMinCol;
    public TableColumn productMaxCol;
    public TableColumn partStockCol;
    public TableColumn partMaxCol;
    public TableColumn partMinCol;
    public TextField partTF;
    public TextField productTF;
    public Button productSearchButton;
    public Button partSearchButton;
    public Button modifyPart;
    public Button deletePart;
    public Button modifyProduct;
    public Button deleteProduct;
    public Button exit;


    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static boolean firstTime = true;
    private void addTestData() {
        if(!firstTime){
            return;
        }
        firstTime = false;
        InHouse sampleInHouse = new InHouse(1, "Brakes", 4.99, 4, 2, 5, 6);

        Inventory.addPart(sampleInHouse);

        Outsourced sampleOutsourced = new Outsourced(2, "Wheel", 4.99, 5, 1, 9,"wgu");

        Inventory.addPart(sampleOutsourced);
    }

    /**
     * The constructor method that initializes the main screen.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Program is initialized");

       // addTestData();

        partTable.setItems(Inventory.getAllParts());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partMinCol.setCellValueFactory(new PropertyValueFactory<>("min"));
        partMaxCol.setCellValueFactory(new PropertyValueFactory<>("max"));

        productTable.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productMinCol.setCellValueFactory(new PropertyValueFactory<>("min"));
        productMaxCol.setCellValueFactory(new PropertyValueFactory<>("max"));
    }

    /**
     * The method that takes the user to the add product screen when it's add button is clicked
     */
    public void onAddProduct(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addProduct.fxml"));
            Parent root = loader.load();
            addProductController apc = loader.getController();
           // apc.load();

            Stage addPartStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene addPartScene = new Scene(root, 1300, 690);
            addPartStage.setTitle("Product Addition");
            addPartStage.setScene(addPartScene);
            addPartStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method that takes the user to the add part screen when it's add button is clicked
     */
    public void onAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addpart.fxml"));
        Stage addPartStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene addPartScene = new Scene(root, 800, 690);
        addPartStage.setTitle("Part Addition");
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    /**
     * The method that searches for a part name or id
     */
    public void getPartSearch(ActionEvent actionEvent) {
        String queryString = partTF.getText();

        ObservableList<Part> partName = lookUpPart(queryString);

        if(partName.size() == 0){
            try {
                int Id = Integer.parseInt(queryString);
                Part part = lookUpPart(Id);
                if (part != null) {
                    partName.add(part);
                }
            }
            catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("No part found");
                alert.showAndWait();
                return;
            }
        }

        partTable.setItems(partName);

        partTF.setText("");
    }

    /**
     * The method that searches for a product name or id.
     */
    public void getProductSearch(ActionEvent actionEvent) {
        String queryString = productTF.getText();

        ObservableList<Product> productName = lookUpProduct(queryString);

        if (productName.size() == 0) {
            try {
                int Id = Integer.parseInt(queryString);
                Product product = lookUpProduct(Id);
                if (product != null) {
                    productName.add(product);
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("No part found");
                alert.showAndWait();
                return;
            }
        }

        productTable.setItems(productName);

        productTF.setText("");
    }

    /**
     * This method closes the application when the exit button is clicked.
     */
    public void onExit(ActionEvent actionEvent) throws IOException {
        Platform.exit();
    }

    /**
     * Method that deletes a product selected by a user.
     */
    public void onDeleteProduct(ActionEvent actionEvent) {
        Product selectedProduct = (Product) productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please select a product to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected Product!");
        Optional<ButtonType> results = alert.showAndWait();

        if(results.isPresent() && results.get() == ButtonType.OK){
            if (selectedProduct.getAllAssociatedParts().size() != 0) {
                Alert newAlert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("This product has parts. Delete them first.");
                alert.showAndWait();
                return;
            }
            Inventory.deleteProduct(selectedProduct);
        }
    }

    /**
     * This method takes the user to a screen that allows him/her to modify the selected product
     */
    public void onModifyProduct(ActionEvent actionEvent) throws IOException {
        Product selectedProduct = (Product) productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please select a product to modify.");
            alert.showAndWait();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyProduct.fxml"));
            Parent root = loader.load();
            modifyProductController mpc = loader.getController();
            int selectedProductIndex = productTable.getSelectionModel().getSelectedIndex();
            mpc.loadProduct(selectedProduct, selectedProductIndex);

            Stage addPartStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene addPartScene = new Scene(root, 1300, 690);
            addPartStage.setTitle("Product Modification");
            addPartStage.setScene(addPartScene);
            addPartStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that deletes a part selected by the user.
     */
    public void onDeletePart(ActionEvent actionEvent) {
        Part selectedPart = (Part) partTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please select a part to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected Part!");
        Optional<ButtonType> results = alert.showAndWait();

        if (results.isPresent() && results.get() == ButtonType.OK) {
            Inventory.deletePart(selectedPart);
            System.out.print("test");
        }
    }

        /**
         * The method that takes the user to the modify part screen when it's modify button is clicked
         */
        public void onModifyPart (ActionEvent actionEvent) throws IOException {
            Part selectedPart = (Part) partTable.getSelectionModel().getSelectedItem();
            if (selectedPart == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Please select a part to modify.");
                alert.showAndWait();
                return;
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyPart.fxml"));
                Parent root = loader.load();
                modifyPartController mpe = loader.getController();
                mpe.loadPart(selectedPart);

                Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene addPartScene = new Scene(root, 1300, 690);
                addPartStage.setTitle("Product Addition");
                addPartStage.setScene(addPartScene);
                addPartStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }