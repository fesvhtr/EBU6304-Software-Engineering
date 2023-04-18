package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
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
 * @create 2020-07-23 1:14
 */
public class DeviceEditController implements Initializable {
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

    @FXML
    private JFXTextField userField;

    @FXML
    private JFXToggleButton isOwnedToggle;

    private DeviceManagementController deviceManagementController;
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
        String user = userField.getText();
        boolean isOwned = isOwnedToggle.isSelected();
        boolean isRent = false;
        if (!isOwned && !user.equals("")) {
            isRent = true;
        }

        if (name.equals("") || spec.equals("") || description.equals("") ) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Try again");
            alert.setHeaderText("Empty Value is not allowed.");
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
        DeviceManager.getInstance().addDevice(new Device(name, type, description, spec, "已关机", user, isRent, isOwned));
        Alert info = new Alert(Alert.AlertType.INFORMATION,"New role have been successfully saved!");
        info.showAndWait();
        deviceManagementController.initialize(null,null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    public void setParentController(DeviceManagementController controller) {
        deviceManagementController = controller;
    }
    public void setDevice(Device device) {
        inDevice = device;
        idField.setText(device.getId());
        nameField.setText(device.getName());
        specField.setText(device.getSpec());
        descriptionField.setText(device.getDescription());
        userField.setText(device.getUser());
        isOwnedToggle.setSelected(device.isOwned());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
//        idField.setText("PRO" + IdGenerator.getCode());
//        List<Type> types = DeviceType.getInstance().getTypes();
//        for (Type t : types) {
//            typeObservableList.add(t);
//        }
//        typeComboBox.setItems(typeObservableList);
    }
}
