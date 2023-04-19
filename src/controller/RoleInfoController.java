package controller;

import entity.Device;
import entity.DeviceManager;
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
    private TableView<Device> table;

    @FXML
    private TableColumn<Device, String> idCol;

    @FXML
    private TableColumn<Device, String> nameCol;

    @FXML
    private TableColumn<Device, String> typeCol;

    @FXML
    private TableColumn<Device, String> specCol;

    @FXML
    private TableColumn<Device, String> descriptionCol;

    @FXML
    private TableColumn<Device, String> statusCol;

    @FXML
    private TableColumn<Device, String> rentStatusCol;

    @FXML
    private TableColumn<Device, String> userCol;

    @FXML
    void delHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Device selectedDevice = table.getSelectionModel().getSelectedItem();
            if (selectedDevice.getRentStatus().equals("已被租用")) {
                Alert nullwarning = new Alert(Alert.AlertType.WARNING, "无法删除所选设备");
                nullwarning.setTitle("提示：该设备正被租去使用哦");
                nullwarning.setHeaderText("已被租用的设备不能删除");
                nullwarning.show();
            } else {
                Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION, "确定删除" + selectedDevice.getName() + "吗？");
                delWarning.setHeaderText("删除确认");
                delWarning.setTitle("稍等下。。");
                delWarning.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        table.getItems().remove(selectedDevice);
                        DeviceManager.getInstance().delDevice(selectedDevice);
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
            Device selectedDevice = table.getSelectionModel().getSelectedItem();
            DeviceEditController controller = (DeviceEditController) ViewManager.newWindow("DeviceEdit.fxml");
            controller.setDevice(selectedDevice);
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
            Device selectedDevice = table.getSelectionModel().getSelectedItem();
            selectedDevice.setStatus("闲置中");
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
        DeviceEditController controller = (DeviceEditController) ViewManager.newWindow("DeviceEdit.fxml");
        controller.setParentController(this);
    }

    @FXML
    void shutDownHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Device selectedDevice = table.getSelectionModel().getSelectedItem();
            selectedDevice.setStatus("已关闭");
            initialize(null,null);
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个设备");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个设备被选中要启动");
            nullWarning.show();
        }
    }

    private ObservableList<Device> deviceObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deviceObservableList.clear();
        List<Device> devices = DeviceManager.getInstance().getDevices();
        for (Device d : devices) {
            deviceObservableList.add(d);
        }
        table.setItems(deviceObservableList);
        idCol.setCellValueFactory(new PropertyValueFactory<Device, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Device, String>("type"));

    }


}
