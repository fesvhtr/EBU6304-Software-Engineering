package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.UserConfigManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.ViewManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

/**
 * The controller for the login page.
 */
public class LoginController implements Initializable {
    @FXML
    private JFXTextField accountTextFiield;

    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private ToggleGroup roleGroup;

    @FXML
    private FontAwesomeIconView exitButton;

    /**
     * Initialize the login page.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleGroup = new ToggleGroup();

    }

    /**
     * Login to the system.
     * @throws IOException If an input or output exception occurred
     */
    @FXML
    private void loginHandled() throws IOException {
        String password = passwordField.getText();
        if (password.equals("")) {
        } else if (password.indexOf(" ") >= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Check and try again");
            alert.setHeaderText("Password cannot contain spaces");
            alert.show();
        } else if (UserConfigManager.getInstance().getUserConfig().getPassword().equals(password)) {
            ViewManager.newWindow("StudentMenu.fxml");
            System.out.println("log in");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Check it and try again\n");
            alert.setHeaderText("Wrong password");
            alert.show();
        }


    }

    /**
     * Close the window.
     */
    private void closeWindow() {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Close the window.
     * @param event The mouse event.
     */
    @FXML
    void close(MouseEvent event) {
        exit();
    }

}
