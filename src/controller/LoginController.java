package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;


public class LoginController implements Initializable {
    @FXML
    private JFXTextField accountTextFiield;

    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private ToggleGroup roleGroup;
    @FXML
    RadioButton superAdminButton;
    @FXML
    private FontAwesomeIconView exitButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleGroup = new ToggleGroup();
        superAdminButton.setToggleGroup(roleGroup);
        superAdminButton.setOnAction(e -> {
            accountTextFiield.setText("888");
            passwordField.setText("888");
        });
    }

    @FXML
    private void loginHandled() {
        String account = accountTextFiield.getText();
        String password = passwordField.getText();
        if (account.equals("") && password.equals("")) {
        } else if (account.indexOf(" ") >= 0 || password.indexOf(" ") >= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Check and try again");
            alert.setHeaderText("Both username and password cannot contain spaces");
            alert.show();
        } else if (roleGroup.getSelectedToggle() == superAdminButton && UserManager.getInstance().CheckLogin(account, password, 3)) {
            ViewManager.newWindow("StudentMenu.fxml");
            System.out.println("student"+account+"log in");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Check it and try again\n" +
                    "Notes:\n" +
                    "Account number 888\n" +
                    "All passwords with the same account number");
            alert.setHeaderText("Wrong account or password");
            alert.show();
        }


    }

    private void closeWindow() {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }
    @FXML
    void close(MouseEvent event) {
        exit();
    }

    @FXML
    public void register(MouseEvent event) {
    }
}
