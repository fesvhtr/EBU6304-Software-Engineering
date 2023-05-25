package controller;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import view.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * The controller for the information page.
 */
public abstract class InfoController implements Initializable
{
    @FXML
    protected TableView<Object> table;
    protected String file;
    protected Manager manager;

    /**
     * Initialize the page.
     * @param location The location.
     * @param resources The resources.
     */
    @Override
    public abstract void initialize(URL location, ResourceBundle resources);

    /**
     * Add a new item.
     * @param event The event.
     */
    @FXML
    void newHandled(ActionEvent event)
    {
        EditController controller = (EditController) ViewManager.newWindow(file);
        controller.setParentController(this);
    }

    /**
     * Delete an item.
     * @param event The event.
     */
    @FXML
    void delHandled(ActionEvent event)
    {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            Object selectedObj = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION,"It will delete this item." );
            delWarning.setHeaderText("Are you sure?");
            delWarning.setTitle("Warning");
            delWarning.showAndWait().ifPresent(response ->
            {
                if (response == ButtonType.OK)
                {
                    table.getItems().remove(selectedObj);
                    manager.removeItem(selectedObj);
                    initialize(null, null);
                }
            });
        }
        else
        {
            Alert nullwarning = new Alert(Alert.AlertType.WARNING, "Please select item from the table.");
            nullwarning.setTitle("ATTENTION: No item");
            nullwarning.setHeaderText("No item has benn selected.");
            nullwarning.show();
        }

    }

    /**
     * Edit an item.
     * @param event The event.
     */
    public abstract void abstractEditHandled(ActionEvent event);

    /**
     * Edit an item.
     * @param event The event.
     */
    @FXML
    void editHandled(ActionEvent event)
    {
        abstractEditHandled(event);
    }
}
