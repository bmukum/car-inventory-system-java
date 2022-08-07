package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    private ObservableList<Sample> parts = FXCollections.observableArrayList();
    private ObservableList<Sample> products = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int id = 0;
        String name = "";
        int stock = 0;
        int min = 0;
        int max = 0;
        System.out.println("Program is initialized");
        //Check this out with content of sample.java
        parts.add(new Sample(1, "Brakes", 4.99, 3, 2, 25));
        parts.add(new Sample(2, "Tires", 100.99, 4, 2, 90));
        parts.add(new Sample(3, "Wheel", 45.99, 0, 2, 5));
        products.add(new Sample(4, "Throttle", 456.99, 1, 2, 6));
        products.add(new Sample(5, "Knots", 1.99, 20, 2, 50));
        products.add(new Sample(6, "Frame", 2.99, 4, 2, 12));


        partTable.setItems(parts);
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partMinCol.setCellValueFactory(new PropertyValueFactory<>("min"));
        partMaxCol.setCellValueFactory(new PropertyValueFactory<>("max"));

        productTable.setItems(products);
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productMinCol.setCellValueFactory(new PropertyValueFactory<>("min"));
        productMaxCol.setCellValueFactory(new PropertyValueFactory<>("max"));
    }

    public void onDeleteButtonClick(ActionEvent actionEvent) {
        System.out.println("Modify is clicked!");
    }

    public void onExitButtonClick(ActionEvent actionEvent) {
        System.out.println("Exit is clicked!");
    }

    public void onAddProduct(ActionEvent actionEvent) {
    }

    public void onAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/part.fxml"));
        Stage addPartStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene addPartScene = new Scene(root, 800, 690);
        addPartStage.setTitle("Part Addition");
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    public void getPartSearch(ActionEvent actionEvent) {
        String queryString = partTF.getText();

        ObservableList<Sample> partName = searchByPartName(queryString);

        if(partName.size() == 0){
            try {
                int Id = Integer.parseInt(queryString);
                Sample part = getPartById(Id);
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

    private ObservableList<Sample> searchByPartName(String partialName) {
        ObservableList<Sample> givenParts = FXCollections.observableArrayList();

        ObservableList<Sample> allParts = Sample.getParts();

        for(Sample part : allParts){
            if(part.getName().contains(partialName)) {
                givenParts.add(part);
            }
        }

        return givenParts;
    }

    private ObservableList<Sample> searchByProductName(String partialName) {
        ObservableList<Sample> givenProducts = FXCollections.observableArrayList();

        ObservableList<Sample> allProducts = Sample.getProducts();

        for(Sample part : allProducts){
            if(part.getName().contains(partialName)) {
                givenProducts.add(part);
            }
        }

        return givenProducts;
    }

    private Sample getPartById(int Id){
        ObservableList<Sample> allParts = Sample.getParts();
        for(int i=0; i < allParts.size(); i++){
            Sample part = allParts.get(i);
            if(part.getId() == Id){
                return part;
            }
        }
        return null;
    }

    private Sample getProductById(int Id){
        ObservableList<Sample> allProducts = Sample.getProducts();
        for(int i=0; i < allProducts.size(); i++){
            Sample product = allProducts.get(i);
            if(product.getId() == Id){
                return product;
            }
        }
        return null;
    }



    public void getProductSearch(ActionEvent actionEvent) {
        String queryString = productTF.getText();

        ObservableList<Sample> productName = searchByProductName(queryString);

        if (productName.size() == 0) {
            try {
                int Id = Integer.parseInt(queryString);
                Sample product = getProductById(Id);
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
}