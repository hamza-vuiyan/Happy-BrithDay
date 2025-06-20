package Application.Controllers;

import Application.DBconnect;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class findController {

    public Label nameLabel;
    public Label dateLabel;
    public Label statusLabel;

    public TextField idToFind;

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

    public void searchButton(ActionEvent event) throws Exception{
        String ID = idToFind.getText();
        ArrayList<String> l = new ArrayList<String>();
        l = DBconnect.searchStudent(ID);
        if(!l.isEmpty()){
            nameLabel.setText((String)l.get(0));
            dateLabel.setText((String)l.get(l.size()-1));
        }
        else{
            statusLabel.setText("Invalid ID");
        }
        l.clear();
    }
    

    
}
