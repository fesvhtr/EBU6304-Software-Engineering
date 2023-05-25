package controller;

import entity.Module;
import entity.ModuleManager;
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

/**
 * The controller for the role information page.
 */
public class RoleInfoController extends InfoController
{

    @FXML
    private TableView<Object> table;
    @FXML
    private TableColumn<Object, String> titleCol;
    @FXML
    private TableColumn<Object, String> startCol;
    @FXML
    private TableColumn<Object, String> endCol;
    @FXML
    private TableColumn<Object, String> desCol;

    private ObservableList<Object> roleObservableList = FXCollections.observableArrayList();

    /**
     * Initialize the role information page.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        roleObservableList.clear();
        List<Role> roles = RoleManager.getInstance().getList();
        for (Role r : roles)
        {
            roleObservableList.add(r);
        }
        table.setItems(roleObservableList);

        titleCol.setCellValueFactory(new PropertyValueFactory<Object, String >("title"));
        startCol.setCellValueFactory(new PropertyValueFactory<Object, String >("startDate"));
        endCol.setCellValueFactory(new PropertyValueFactory<Object, String >("endDate"));
        desCol.setCellValueFactory(new PropertyValueFactory<Object, String >("description"));

        file = "RoleEdit.fxml";
        manager = RoleManager.getInstance();
    }

    /**
     * Edit the selected role.
     * @param event The event that the edit button is clicked.
     */
    public void abstractEditHandled(ActionEvent event)
    {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            Role selectedRole = (Role) table.getSelectionModel().getSelectedItem();
            RoleEditController controller = (RoleEditController) ViewManager.newWindow("RoleEdit.fxml");
            controller.setRole(selectedRole);
            controller.setParentController(this);
        }
        else
        {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please select item from the table.");
            nullWarning.setTitle("ATTENTION: No item");
            nullWarning.setHeaderText("No item has benn selected.");
            nullWarning.show();
        }
    }
}
