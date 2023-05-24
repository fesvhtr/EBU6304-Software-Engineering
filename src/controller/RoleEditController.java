package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Role;
import entity.RoleManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.validation.NumberValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * The controller for the role edit page.
 */
public class RoleEditController extends EditController
{
    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    private JFXTextField stratField;

    @FXML
    private JFXTextField titleField;

    @FXML
    private JFXTextArea descriptionField;

    @FXML
    private JFXTextField endField;

    @FXML
    private Label descriptionCountLabel;

    private Role role;


    private final int MAX_DESCRIPTION_LENGTH = 200;
    private String des = "";

    /**
     * Initialize the role edit page.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        descriptionField.setText("");
        descriptionField.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().length() <= 200 ? change : null));
        descriptionField.textProperty().addListener((observable, oldValue, newValue) ->
                descriptionCountLabel.setText(newValue.length() + "/200"));
    }


    /**
     * Save the role information.
     * @param event The mouse event.
     */
    @FXML
    void saveHandled(ActionEvent event)
    {
        String title = titleField.getText();
        String start = stratField.getText();
        String description = descriptionField.getText();
        String end = endField.getText();

        if (title.equals("") || start.equals("")|| end.equals("") )
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Try again");
            alert.setHeaderText("Empty Value is not allowed.");
            alert.show();
            return;
        } else if (!isValidDate(start) || !isValidDate(end) ){
            return;
        }

        if (role != null)
        {
            RoleManager.getInstance().removeItem(role);
        }
        RoleManager.getInstance().addItem(new Role(title, description, start,  end));
        Alert info = new Alert(Alert.AlertType.INFORMATION,"info have been successfully saved!");
        info.showAndWait();
        controller.initialize(null,null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }


    /**
     * Check if the date is valid.
     * @param dateStr The date string.
     * @return True if the date is valid, false otherwise.
     */
    private static boolean isValidDate(String dateStr)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try
        {
            dateFormat.parse(dateStr);
            return true;
        }
        catch (ParseException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Try again");
            alert.setHeaderText("Date format is not valid, please input in the format of (yyyy-MM-dd).");
            alert.show();
            return false;
        }
    }

    /**
     * Set the role to be edited.
     * @param role The role to be edited.
     */
    @FXML
    public void setRole(Role role) {
        this.role = role;
        titleField.setText(role.getTitle());
        stratField.setText(role.getStartDate());
        endField.setText(role.getEndDate());
        descriptionField.setText(role.getDescription());
    }
}
