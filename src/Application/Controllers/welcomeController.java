package Application.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class welcomeController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void addEntries(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/FXMLS/addUi.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchEntries(ActionEvent event) {
    }

    public void updateEntries(ActionEvent event) {
    }

    public void viewEntries(ActionEvent event) {
    }

    public void delEntries(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/FXMLS/delUi.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
