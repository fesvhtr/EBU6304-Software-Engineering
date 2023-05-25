package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The controller for the module type management page.
 */
public class ModuleTypeManagementController implements Initializable {
    @FXML
    private JFXListView<Type> list;

    @FXML
    private Label title;

    @FXML
    private JFXTextField newTypeField;

    @FXML
    private JFXButton configureButton;

    private ObservableList<Type> moduleTypeObservableList = FXCollections.observableArrayList();

    /**
     * Initialize the page.
     * @param location The location.
     * @param resources The resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moduleTypeObservableList.clear();
        List<Type> moduleTypes = ModuleTypeManager.getInstance().getList();
        for (Type t : moduleTypes) {
            moduleTypeObservableList.add(t);
        }
        title.setText("Module Type Management");
        list.setItems(moduleTypeObservableList);
    }

    /**
     * Add a new type.
     * @param event The mouse event.
     */
    @FXML
    void newTypeHandled(ActionEvent event) {
        newTypeField.setVisible(true);
        configureButton.setVisible(true);

    }

    /**
     * Delete the selected type.
     * @param event The mouse event.
     */
    @FXML
    void delTypeHandled(ActionEvent event) {
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Type selectedType = list.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION,"You want to delete" + selectedType + "?");
            delWarning.setHeaderText("Delete Confirm");
            delWarning.setTitle("Wait...");
            delWarning.showAndWait().ifPresent(response ->{
                if (response == ButtonType.OK) {
                    list.getItems().remove(selectedType);
                    ModuleTypeManager.getInstance().removeItem(selectedType);
                    initialize(null, null);
                }
            });
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please check one item in the table");
            nullWarning.setTitle("Hint: No item is selected!");
            nullWarning.setHeaderText("No item is selected!");
            nullWarning.show();
        }
    }

    /**
     * Confirm the new type.
     * @param event The mouse event.
     */
    @FXML
    void configureHandled(ActionEvent event) {
        if (newTypeField.getText().equals("")) return;
        ModuleTypeManager.getInstance().addItem(new Type(newTypeField.getText()));
        initialize(null, null);
        newTypeField.setText("");
        newTypeField.setVisible(false);
        configureButton.setVisible(false);
    }
}
