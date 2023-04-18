package controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Device;
import entity.DeviceManager;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.FileOperator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class RentDeviceController implements Initializable {
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
    private FontAwesomeIconView exitButton;

    private ObservableList<Device> deviceObservableList = FXCollections.observableArrayList();

    private MyDeviceController myDeviceController;

    public void setParentController(MyDeviceController controller) {
        myDeviceController = controller;
    }

    @FXML
    void close(MouseEvent event) {
        myDeviceController.initialize(null, null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void rentHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Device selectedDevice = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION, "确定租用" + selectedDevice.getName() + "吗？");
            delWarning.setHeaderText("租用确认");
            delWarning.setTitle("提示");
            delWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    selectedDevice.setRent(true);
                    selectedDevice.setUser(UserManager.getInstance().getCurrentUser().getName());
                    FileOperator.writeData(deviceObservableList, "dev.json");
                    initialize(null, null);
                }
            });
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deviceObservableList.clear();
        List<Device> devices = DeviceManager.getInstance().getDevices();
        for (Device d : devices) {
            if (d.getRentStatus().equals("未被租用"))
            deviceObservableList.add(d);
        }
        table.setItems(deviceObservableList);
        idCol.setCellValueFactory(new PropertyValueFactory<Device, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Device, String>("type"));
        specCol.setCellValueFactory(new PropertyValueFactory<Device, String>("spec"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Device, String>("description"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Device, String>("status"));
    }
}
