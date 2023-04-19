package controller;

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
    private TableColumn<Role, String> idCol;

    @FXML
    private TableColumn<Role, String> nameCol;

    @FXML
    private TableColumn<Role, String> typeCol;

    @FXML
    private TableColumn<Role, String> specCol;

    @FXML
    private TableColumn<Role, String> descriptionCol;

    @FXML
    private TableColumn<Role, String> statusCol;

    @FXML
    private TableColumn<Role, String> rentStatusCol;

    @FXML
    private TableColumn<Role, String> userCol;

    @FXML
    void delHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Role selectedRole = table.getSelectionModel().getSelectedItem();
            if (selectedRole.getRentStatus().equals("已被租用")) {
                Alert nullwarning = new Alert(Alert.AlertType.WARNING, "无法删除所选设备");
                nullwarning.setTitle("提示：该设备正被租去使用哦");
                nullwarning.setHeaderText("已被租用的设备不能删除");
                nullwarning.show();
            } else {
                Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION, "确定删除" + selectedRole.getName() + "吗？");
                delWarning.setHeaderText("删除确认");
                delWarning.setTitle("稍等下。。");
                delWarning.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        table.getItems().remove(selectedRole);
                        RoleManager.getInstance().delDevice(selectedRole);
                        initialize(null, null);
                    }
                });
            }
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个设备");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个设备被选中要删除");
            nullWarning.show();
        }
    }

    @FXML
    void editHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Role selectedRole = table.getSelectionModel().getSelectedItem();
            RoleEditController controller = (RoleEditController) ViewManager.newWindow("RoleEdit.fxml");
            controller.setDevice(selectedRole);
            controller.setParentController(this);
        }else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个设备");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个设备被选中要编辑");
            nullWarning.show();
        }
    }

    @FXML
    void launchHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Role selectedRole = table.getSelectionModel().getSelectedItem();
            selectedRole.setStatus("闲置中");
            initialize(null,null);
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个设备");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个设备被选中要启动");
            nullWarning.show();
        }
    }

    @FXML
    void newHandled(ActionEvent event) {
        RoleEditController controller = (RoleEditController) ViewManager.newWindow("RoleEdit.fxml");
        controller.setParentController(this);
    }

    @FXML
    void shutDownHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Role selectedRole = table.getSelectionModel().getSelectedItem();
            selectedRole.setStatus("已关闭");
            initialize(null,null);
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个设备");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个设备被选中要启动");
            nullWarning.show();
        }
    }

    private ObservableList<Role> roleObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleObservableList.clear();
        List<Role> roles = RoleManager.getInstance().getDevices();
        for (Role d : roles) {
            roleObservableList.add(d);
        }
        table.setItems(roleObservableList);
        idCol.setCellValueFactory(new PropertyValueFactory<Role, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Role, String>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Role, String>("type"));

    }


}
