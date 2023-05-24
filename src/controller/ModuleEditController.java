package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Module;
import entity.ModuleManager;
import entity.ModuleTypeManager;
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
 * The controller for the module edit page.
 */
public class ModuleEditController extends EditController
{
    @FXML
    private JFXTextField creditField;
    @FXML
    private JFXTextField markField;
    @FXML
    private JFXTextField descriptionField;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField idField;

    @FXML
    private JFXComboBox<Type> typeComboBox;

    private Module inModule;

    /**
     * Initialize the page.
     * @param location The location.
     * @param resources The resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
        idField.setText("MOD" + IdGenerator.getCode());
        List<Type> types = ModuleTypeManager.getInstance().getList();
        for (Type t : types) {
            typeObservableList.add(t);
        }
        typeComboBox.setItems(typeObservableList);
    }

    /**
     * Set the module to be edited.
     * @param module The module to be edited.
     */
    public void setModule(Module module)
    {
        inModule = module;
        idField.setText(module.getId());
        nameField.setText(module.getName());
        creditField.setText(String.format("%.2f", module.getCredit()));
        markField.setText(String.format("%.2f", module.getMark()));
        descriptionField.setText(module.getDescription());
    }

    /**
     * Save the module.
     * @param event The mouse event.
     */
    @FXML
    void saveHandled(ActionEvent event)
    {
        String id = idField.getText();
        String name = nameField.getText();
        String credit = creditField.getText();
        String mark = markField.getText();
        String description = descriptionField.getText();

        if (name.equals("") || credit.equals("") || description.equals("") || mark.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("Can't be null");
            alert.show();
            return;
        }
        else if (typeComboBox.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("No type is selected");
            alert.show();
            return;
        }
        String type = typeComboBox.getSelectionModel().getSelectedItem().toString();
        if (inModule != null)
        {
            ModuleManager.getInstance().removeItem(inModule);
        }
        ModuleManager.getInstance().addItem(new Module(id, name, type, Float.parseFloat(mark), Float.parseFloat(credit), description));
        controller.initialize(null,null);
        Alert info = new Alert(Alert.AlertType.INFORMATION,"New Module saved");
        info.showAndWait();
        controller.initialize(null,null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

}
