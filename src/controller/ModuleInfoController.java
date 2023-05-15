package controller;

import entity.Module;
import entity.ModuleManager;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ModuleInfoController implements Initializable {
    @FXML
    private TableView<Module> table;

    @FXML
    private TableColumn<Module, String> idCol;

    @FXML
    private TableColumn<Module, String> nameCol;

    @FXML
    private TableColumn<Module, String> typeCol;
    @FXML
    private TableColumn<Module, String> creditCol;
    @FXML
    private TableColumn<Module, String> markCol;

    @FXML
    private TableColumn<Module, String> descriptionCol;

    private ObservableList<Module> moduleObservableList = FXCollections.observableArrayList();

    private ArrayList<Object> my_eva = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moduleObservableList.clear();
        List<Module> modules = ModuleManager.getInstance().getModule();
        for (Module p : modules) {
            moduleObservableList.add(p);
        }
        table.setItems(moduleObservableList);
        idCol.setCellValueFactory(new PropertyValueFactory<Module, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Module, String>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Module, String>("type"));
        markCol.setCellValueFactory(new PropertyValueFactory<Module, String>("mark"));
        creditCol.setCellValueFactory(new PropertyValueFactory<Module, String>("credit"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Module, String>("description"));

        //test eva func
        System.out.println(ModuleManager.getInstance().getEva().toString());
        my_eva = ModuleManager.getInstance().getEva();

    }

    @FXML
    void delHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Module selectedModule = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION,"Confirm to delete " + selectedModule.getName() + "?");
            delWarning.setHeaderText("delete confirmed");
            delWarning.setTitle("Hold on..");
            delWarning.showAndWait().ifPresent(response ->{
                if (response == ButtonType.OK) {
                    table.getItems().remove(selectedModule);
                    ModuleManager.getInstance().delModule(selectedModule);
                    initialize(null, null);
                }
            });
        } else {
            Alert nullwarning = new Alert(Alert.AlertType.WARNING, "Please select a module");
            nullwarning.setTitle("No item selected");
            nullwarning.setHeaderText("No module to delete");
            nullwarning.show();
        }

    }

    @FXML
    void editHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Module selectedModule = table.getSelectionModel().getSelectedItem();
            ModuleEditController controller = (ModuleEditController) ViewManager.newWindow("ModuleEdit.fxml");
            controller.setModule(selectedModule);
            controller.setParentController(this);
        }else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please select a module");
            nullWarning.setTitle("No item selected");
            nullWarning.setHeaderText("No module to edit");
            nullWarning.show();
        }
    }

    @FXML
    void newHandled(ActionEvent event) {
        ModuleEditController controller = (ModuleEditController) ViewManager.newWindow("ModuleEdit.fxml");
        controller.setParentController(this);
    }

    @FXML
    void GPAHandled(ActionEvent event) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION, "Here is your Mark Report");
        infoAlert.setTitle("Mark Report");
        infoAlert.setHeaderText("Yor GPA (out of 100 scale) is " + my_eva.get(0) +"\nYor GPA (out of 4.0 scale) is "
                + my_eva.get(1)+ "\nYor Degree level is " + my_eva.get(2));
        infoAlert.show();
    }


}
