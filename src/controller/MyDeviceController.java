package controller;

import entity.Device;
import entity.DeviceManager;
import entity.FactoryAdmin;
import entity.UserManager;
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


public class MyDeviceController implements Initializable {
    ObservableList<Device> deviceList = FXCollections.observableArrayList();
    FactoryAdmin factoryAdmin;

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
    private TableColumn<Device, String> resourceCol;


    private ObservableList<Device> deviceObservableList = FXCollections.observableArrayList();
    @FXML
    void newDevice(ActionEvent event) {
        MyNewDeviceController controller = (MyNewDeviceController) ViewManager.newWindow("MyNewDevice.fxml");
        controller.setParentController(this);
    }

    @FXML
    void rentDevice(ActionEvent event) {
        RentDeviceController controller = (RentDeviceController) ViewManager.newWindow("RentDevice.fxml");
        controller.setParentController(this);
    }

    @FXML
    void returnDevice(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Device selectedDevice = table.getSelectionModel().getSelectedItem();
            if (selectedDevice.getResource().equals("自有设备")) {
                Alert nullWarning = new Alert(Alert.AlertType.WARNING, "无法归还所选设备");
                nullWarning.setTitle("提示");
                nullWarning.setHeaderText("自己的设备不能归还哦");
                nullWarning.show();
            } else {
                Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION, "确定归还" + selectedDevice.getName() + "吗？");
                delWarning.setHeaderText("归还确认");
                delWarning.setTitle("稍等下。。");
                delWarning.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        selectedDevice.setRent(false);
                        selectedDevice.setUser("");
                        table.getItems().remove(selectedDevice);
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
    void delDevice(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Device selectedDevice = table.getSelectionModel().getSelectedItem();
            if (selectedDevice.getResource().equals("租用设备")) {
                Alert nullWarning = new Alert(Alert.AlertType.WARNING, "无法删除所选设备");
                nullWarning.setTitle("提示");
                nullWarning.setHeaderText("租用别人的设备不能私自删除哦");
                nullWarning.show();
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
    void shutDownDevice(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Device selectedDevice = table.getSelectionModel().getSelectedItem();
            selectedDevice.setStatus("已关机");
            initialize(null,null);
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个设备");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个设备被选中要启动");
            nullWarning.show();
        }
    }

    @FXML
    void launchDevice(ActionEvent event) {
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
    void modifyDevice(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Device selectedDevice = table.getSelectionModel().getSelectedItem();
            MyNewDeviceController controller = (MyNewDeviceController) ViewManager.newWindow("MyNewDevice.fxml");
            controller.setDevice(selectedDevice);
            controller.setParentController(this);
        }else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个设备");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个设备被选中要编辑");
            nullWarning.show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deviceObservableList.clear();
        List<Device> devices = DeviceManager.getInstance().getDevices();
        for (Device d : devices) {
            if (d.getUser().equals(UserManager.getInstance().getCurrentUser().getName()))
                deviceObservableList.add(d);
        }
        table.setItems(deviceObservableList);
        idCol.setCellValueFactory(new PropertyValueFactory<Device, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Device, String>("type"));
        specCol.setCellValueFactory(new PropertyValueFactory<Device, String>("spec"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Device, String>("description"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Device, String>("status"));
        resourceCol.setCellValueFactory(new PropertyValueFactory<Device, String>("resource"));
    }
}
