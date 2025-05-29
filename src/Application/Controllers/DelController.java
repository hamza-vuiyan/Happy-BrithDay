package Application.Controllers;

import Application.DBconnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DelController {


    public TextField idToDel;
    public Label statusLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML

    public void backToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/FXMLS/welcome.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void delButton(ActionEvent event) throws IOException {
        String id = idToDel.getText();

        String status = DBconnect.deleteStudentData(id);
        statusLabel.setText(status);
    }
}
