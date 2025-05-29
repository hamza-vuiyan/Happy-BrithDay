package Application.Controllers;

import Application.DBconnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Application.DBconnect.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddController {

    public TextField idInput;
    public TextField nameInput;
    public DatePicker datePicker;
    public Label statusLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void backToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/FXMLS/welcome.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void submitBtn(ActionEvent event) {
        String ID = idInput.getText();
        String name = nameInput.getText();
        String date = datePicker.getValue().toString();
        String status = DBconnect.insertStudentData(ID, name, date);

        statusLabel.setText(status);
    }


}
