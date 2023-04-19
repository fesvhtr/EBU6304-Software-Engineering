package controller;

import entity.Module;
import entity.Role;
import entity.RoleManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class RoleInfoController implements Initializable {

    @FXML
    private TableView<Role> table;

    @FXML
    private TableColumn<Role, String> titleCol;

    @FXML
    private TableColumn<Role, String> startCol;

    @FXML
    private TableColumn<Role, String> endCol;

    @FXML
    private TableColumn<Role, String> desCol;

    private ObservableList<Role> roleObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleObservableList.clear();
        List<Role> roles = RoleManager.getInstance().getRoles();
        for (Role r : roles){
            roleObservableList.add(r);
        }
        table.setItems(roleObservableList);

        titleCol.setCellValueFactory(new PropertyValueFactory<Role, String >("title"));
        startCol.setCellValueFactory(new PropertyValueFactory<Role, String >("startDate"));
        endCol.setCellValueFactory(new PropertyValueFactory<Role, String >("endDate"));
        desCol.setCellValueFactory(new PropertyValueFactory<Role, String >("description"));
    }

}
