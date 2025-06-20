package Application.Controllers;


import java.io.IOException;

import Application.DBconnect;
import Application.ModelClass.Students;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import java.net.URL;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class viewController  implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void backToHome(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/Application/FXMLS/welcome.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public TableView<Students> tableView;

    @FXML
    public TableColumn<Students, String> idCol;
    @FXML
    public TableColumn<Students, String> nameCol;
    @FXML
    public TableColumn<Students, String> dateCol;

    public ObservableList<Students> obsvList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        ArrayList<Students> studentList = DBconnect.viewStudents();
        obsvList.addAll(studentList);
        tableView.setItems(obsvList);
    }
}
