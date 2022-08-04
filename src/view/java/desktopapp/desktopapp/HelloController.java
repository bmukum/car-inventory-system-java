package desktopapp.desktopapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label aL;

    @FXML
    private Label mL;

    @FXML
    private Label dL;
    @FXML
    private Label eL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Program is initialized");
    }

    public void onAddButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/main/java/resources/desktopapp.desktopapp/part.fxml"));
        Stage addPartStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene addPartScene = new Scene(root, 600, 400);
        addPartStage.setTitle("Part Addition");
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }


//    public void onAddButtonClick(ActionEvent actionEvent) {
//        System.out.println("Add is clicked!");
//        aL.setText("Item added");
//    }

    public void onModifyButtonClick(ActionEvent actionEvent) {
        System.out.println("Modify is clicked!");
        mL.setText("Waiting to modify");
    }

    public void onDeleteButtonClick(ActionEvent actionEvent) {
        System.out.println("Modify is clicked!");
        dL.setText("Item deleted");
    }

    public void onExitButtonClick(ActionEvent actionEvent) {
        System.out.println("Exit is clicked!");
        eL.setText("Item deleted");
    }
}