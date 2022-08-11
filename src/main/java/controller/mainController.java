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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static model.Inventory.lookUpPart;
import static model.Inventory.lookUpProduct;

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

    private ObservableList<Part> allParts = FXCollections.observableArrayList();
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int id = 0;
        String name = "";
        int stock = 0;
        int min = 0;
        int max = 0;
        System.out.println("Program is initialized");

        addTestData();

        partTable.setItems(Inventory.getAllParts());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partMinCol.setCellValueFactory(new PropertyValueFactory<>("min"));
        partMaxCol.setCellValueFactory(new PropertyValueFactory<>("max"));
    }

    public void onDeleteButtonClick(ActionEvent actionEvent) {
        System.out.println("Modify is clicked!");
    }

    public void onExitButtonClick(ActionEvent actionEvent) {
        System.out.println("Exit is clicked!");
    }

    public void onAddProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addproduct.fxml"));
        Stage addPartStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene addPartScene = new Scene(root, 1377, 683);
        addPartStage.setTitle("Product Addition");
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    public void onAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addpart.fxml"));
        Stage addPartStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene addPartScene = new Scene(root, 800, 690);
        addPartStage.setTitle("Part Addition");
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

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
                //ingore it
            }
        }

        partTable.setItems(partName);

        partTF.setText("");
    }
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
                //ingore it
            }
        }

        productTable.setItems(productName);

        productTF.setText("");
    }

    public void onExit(ActionEvent actionEvent) {
    }

    public void onDeleteProduct(ActionEvent actionEvent) {
    }

    public void onModifyProduct(ActionEvent actionEvent) throws IOException {
       // playAction();
        Part selectedPart = (Part) partTable.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyPart.fxml"));
            Parent root = loader.load();
            modifyPartController mpe = loader.getController();
            mpe.loadPart((Part) selectedPart);

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

    public void onDeletePart(ActionEvent actionEvent) {
    }

    public void onModifyPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/modifypart.fxml"));
        Stage addPartStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene addPartScene = new Scene(root, 800, 690);
        addPartStage.setTitle("Part Modification");
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }
}