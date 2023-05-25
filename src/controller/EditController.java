package controller;

import entity.*;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import entity.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * The controller for the edit page.
 */
public abstract class EditController implements Initializable
{
    @FXML
    protected FontAwesomeIconView exitButton;

    protected InfoController controller;

    /**
     * Initialize the page.
     * @param location The URL location.
     * @param resources The resource bundle.
     */
    public abstract void initialize(URL location, ResourceBundle resources);

    /**
     * Set the parent controller.
     * @param controller The parent controller.
     */
    public void setParentController(InfoController controller)
    {
        this.controller = controller;
    }

    /**
     * Save the entity to be edited.
     * @param event The event.
     */
    public abstract void abstractSaveHandled(ActionEvent event);

    /**
     * Save the entity to be edited.
     * @param event The event.
     */
    @FXML
    void saveHandled(ActionEvent event) {
        abstractSaveHandled(event);
    }

    /**
     * Close the window.
     * @param event The event.
     */
    @FXML
    void close(MouseEvent event)
    {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }
}
