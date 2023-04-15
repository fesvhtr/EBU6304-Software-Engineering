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
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-22 19:58
 */
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
    private TableColumn<Module, String> specCol;

    @FXML
    private TableColumn<Module, String> descriptionCol;

    private ObservableList<Module> moduleObservableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moduleObservableList.clear();
        List<Module> modules = ModuleManager.getInstance().getProducts();
        for (Module p : modules) {
            moduleObservableList.add(p);
        }
        table.setItems(moduleObservableList);
        idCol.setCellValueFactory(new PropertyValueFactory<Module, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Module, String>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Module, String>("type"));
        specCol.setCellValueFactory(new PropertyValueFactory<Module, String>("spec"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Module, String>("description"));
    }

    @FXML
    void delHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Module selectedModule = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION,"确定删除" + selectedModule.getName() + "吗？");
            delWarning.setHeaderText("删除确认");
            delWarning.setTitle("稍等下。。");
            delWarning.showAndWait().ifPresent(response ->{
                if (response == ButtonType.OK) {
                    table.getItems().remove(selectedModule);
                    ModuleManager.getInstance().delProduct(selectedModule);
                    initialize(null, null);
                }
            });
        } else {
            Alert nullwarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个产品");
            nullwarning.setTitle("提示：未选中任何项哦");
            nullwarning.setHeaderText("没有一个产品被选中要删除");
            nullwarning.show();
        }

    }

    @FXML
    void editHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Module selectedModule = table.getSelectionModel().getSelectedItem();
            ModuleEditController controller = (ModuleEditController) ViewManager.newWindow("ModuleEdit.fxml");
            controller.setProduct(selectedModule);
            controller.setParentController(this);
        }else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个产品");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个产品被选中要编辑");
            nullWarning.show();
        }
    }

    @FXML
    void newHandled(ActionEvent event) {
        ModuleEditController controller = (ModuleEditController) ViewManager.newWindow("ModuleEdit.fxml");
        controller.setParentController(this);
    }


}
