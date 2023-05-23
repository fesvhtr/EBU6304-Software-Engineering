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
import view.ViewManager;

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
                    ModuleType.getInstance().removeType(selectedType);
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
     * Add a new type.
     * @param event The mouse event.
     */
    @FXML
    void newTypeHandled(ActionEvent event) {
        newTypeField.setVisible(true);
        configureButton.setVisible(true);

    }


    @FXML
    void configureHandled(ActionEvent event) {
        if (newTypeField.getText().equals("")) return;
        if (title.getText().equals("Module Type Management")) {
            ModuleType.getInstance().addType(newTypeField.getText());
        } else if (title.getText().equals("产品类别管理")) {
            ModuleType.getInstance().addType(newTypeField.getText());
        }
        initialize(null, null);
        newTypeField.setText("");
        newTypeField.setVisible(false);
        configureButton.setVisible(false);
    }

    private ObservableList<Type> productTypeObservableList = FXCollections.observableArrayList();

    /**
     * Initialize the page.
     * @param location The location.
     * @param resources The resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productTypeObservableList.clear();
        List<Type> productTypes = ModuleType.getInstance().getTypes();
        for (Type t : productTypes) {
            productTypeObservableList.add(t);
        }
        title.setText("Module Type Management");
        list.setItems(productTypeObservableList);
    }
}
