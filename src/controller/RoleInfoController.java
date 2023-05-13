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

    @FXML
    void delHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Role selectedRole = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete" + selectedRole.getTitle() + "?");
            delWarning.setHeaderText("Delete confirm.");
            delWarning.setTitle("Please waiting.");
            delWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    table.getItems().remove(selectedRole);
                    RoleManager.getInstance().delRole(selectedRole);
                    initialize(null, null);
                }
            });
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please select item from the table.");
            nullWarning.setTitle("ATTENTION: No item");
            nullWarning.setHeaderText("No item has benn selected.");
            nullWarning.show();
        }
    }

    @FXML
    void editHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Role selectedRole = table.getSelectionModel().getSelectedItem();
            RoleEditController controller = (RoleEditController) ViewManager.newWindow("RoleEdit.fxml");
            controller.setRole(selectedRole);
            controller.setParentController(this);
        }else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please select item from the table.");
            nullWarning.setTitle("ATTENTION: No item");
            nullWarning.setHeaderText("No item has benn selected.");
            nullWarning.show();
        }
    }

    @FXML
    void newHandled(ActionEvent event) {
        RoleEditController controller = (RoleEditController) ViewManager.newWindow("RoleEdit.fxml");
        controller.setParentController(this);
    }

}
