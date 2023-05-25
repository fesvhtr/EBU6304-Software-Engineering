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

public abstract class EditController implements Initializable
{
    @FXML
    protected FontAwesomeIconView exitButton;

    protected InfoController controller;

    public abstract void initialize(URL location, ResourceBundle resources);

    public void setParentController(InfoController controller)
    {
        this.controller = controller;
    }

    public abstract void abstractSaveHandled(ActionEvent event);

    @FXML
    void saveHandled(ActionEvent event) {
        abstractSaveHandled(event);
    }


    @FXML
    void close(MouseEvent event)
    {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }
}
