package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.IdGenerator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-23 4:52
 */
public class MyNewDeviceController implements Initializable {
    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    private JFXTextField specField;

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXComboBox<Type> typeComboBox;

    @FXML
    private JFXTextField idField;

    @FXML
    private JFXTextField descriptionField;

    private MyDeviceController myDeviceController;
    private Device inDevice;

    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void saveHandled(ActionEvent event) {
        String id = idField.getText();
        String name = nameField.getText();
        String spec = specField.getText();
        String description = descriptionField.getText();
        if (name.equals("") || spec.equals("") || description.equals("") ) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧");
            alert.setHeaderText("信息输入不能有空值");
            alert.show();
            return;
        } else if (typeComboBox.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧");
            alert.setHeaderText("设备类别未选择");
            alert.show();
            return;
        }
        String type = typeComboBox.getSelectionModel().getSelectedItem().toString();
        if (inDevice != null) {
            DeviceManager.getInstance().delDevice(inDevice);
        }
        DeviceManager.getInstance().addDevice(new Device(name, type, description, spec, "已关机", UserManager.getInstance().getCurrentUser().getName(), false, true));
        Alert info = new Alert(Alert.AlertType.INFORMATION,"新的设备已添加");
        info.showAndWait();
        myDeviceController.initialize(null, null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    public void setParentController(MyDeviceController controller) {
        myDeviceController = controller;
    }
    public void setDevice(Device device) {
        inDevice = device;
        idField.setText(device.getId());
        nameField.setText(device.getName());
        specField.setText(device.getSpec());
        descriptionField.setText(device.getDescription());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
        idField.setText("PRO" + IdGenerator.getCode());
        List<Type> types = ActivityType.getInstance().getTypes();
        for (Type t : types) {
            typeObservableList.add(t);
        }
        typeComboBox.setItems(typeObservableList);
    }

}
