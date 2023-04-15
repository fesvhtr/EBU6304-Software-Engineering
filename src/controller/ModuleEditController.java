package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Module;
import entity.ModuleManager;
import entity.ModuleType;
import entity.Type;
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
 * @create 2020-07-22 20:50
 */
public class ModuleEditController implements Initializable {
    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    private JFXTextField specField;

    @FXML
    private JFXTextField descriptionField;

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXComboBox<Type> typeComboBox;

    @FXML
    private JFXTextField idField;

    private Module inModule;
    private ModuleInfoController moduleInfoController;

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
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("Can't be null");
            alert.show();
            return;
        } else if (typeComboBox.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("No type is selected");
            alert.show();
            return;
        }
        String type = typeComboBox.getSelectionModel().getSelectedItem().toString();
        if (inModule != null) {
            ModuleManager.getInstance().delProduct(inModule);
        }
        ModuleManager.getInstance().addProduct(new Module(id, name, type, spec, description));
        moduleInfoController.initialize(null,null);
        Alert info = new Alert(Alert.AlertType.INFORMATION,"New Module saved");
        info.showAndWait();
        moduleInfoController.initialize(null,null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    public void setParentController(ModuleInfoController controller) {
        moduleInfoController = controller;
    }

    public void setProduct(Module module) {
        inModule = module;
        idField.setText(module.getId());
        nameField.setText(module.getName());
        specField.setText(module.getSpec());
        descriptionField.setText(module.getDescription());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
        idField.setText("MOD" + IdGenerator.getCode());
        List<Type> types = ModuleType.getInstance().getTypes();
        for (Type t : types) {
            typeObservableList.add(t);
        }
        typeComboBox.setItems(typeObservableList);
    }
}
