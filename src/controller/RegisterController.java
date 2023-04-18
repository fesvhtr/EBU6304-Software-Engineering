package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.FactoryAdmin;
import entity.Retailer;
import entity.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {

    @FXML
    private JFXRadioButton retailerButton;

    @FXML
    private ToggleGroup roleGroup;

    @FXML
    private JFXRadioButton factoryAdminButton;

    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    private JFXTextField accountField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField contactField;

    @FXML
    private JFXTextField factoryNameField;

    @FXML
    private JFXTextArea factoryDescription;

    @FXML
    private JFXPasswordField passwordConfirmationField;

    private UserManagementController userManagementController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleGroup = new ToggleGroup();
        retailerButton.setToggleGroup(roleGroup);
        factoryAdminButton.setToggleGroup(roleGroup);
    }

    @FXML
    void factoryAdminButtonHandled(ActionEvent event) {
        factoryNameField.setVisible(true);
        factoryDescription.setVisible(true);
    }


    @FXML
    void retailerButtonHandled(ActionEvent event) {
        factoryNameField.setVisible(false);
        factoryDescription.setVisible(false);
    }
    @FXML
    void registHandled(ActionEvent event) {
        String account = accountField.getText();
        String password = passwordField.getText();
        String passwordConfirmation = passwordConfirmationField.getText();
        String name = nameField.getText();
        String contact = contactField.getText();
        String factoryName = factoryNameField.getText();
        String factoryDescriptionText = factoryDescription.getText();
        if (account.equals("") && password.equals("")) {
            return;
        } else if (account.equals("") || password.equals("") || passwordConfirmation.equals("") || name.equals("") || contact.equals(""))  {
            Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧");
            alert.setHeaderText("信息输入不能有空值");
            alert.show();
            return;
        } else if (!password.equals(passwordConfirmation)) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧");
            alert.setHeaderText("两次密码输入不一致");
            alert.show();
            return;
        }
        if (roleGroup.getSelectedToggle() == retailerButton) {
            Retailer retailer = new Retailer(account, password, contact, name);
            if(UserManager.getInstance().addRetailer(retailer)) {
                Alert regSuccess = new Alert(Alert.AlertType.INFORMATION, "新人已可用");
                regSuccess.setHeaderText("注册成功");
                regSuccess.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "换一个账号再注册吧");
                alert.setHeaderText("已存在相同账号的用户");
                alert.show();
            }
        } else {
            if (factoryName.equals("") || factoryDescriptionText.equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧");
                alert.setHeaderText("信息输入不能有空值");
                alert.show();
            } else {
                FactoryAdmin factoryAdmin = new FactoryAdmin(account, password, contact, name, factoryName, factoryDescriptionText);
                if (UserManager.getInstance().addFactoryAdmin(factoryAdmin)) {
                    Alert regSuccess = new Alert(Alert.AlertType.INFORMATION, "新人已可用");
                    regSuccess.setHeaderText("注册成功");
                    regSuccess.showAndWait();
                    if (userManagementController != null) {
                        userManagementController.initialize(null, null);
                    }
                    Stage currentStage = (Stage) exitButton.getScene().getWindow();
                    currentStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧");
                    alert.setHeaderText("已存在相同账号的用户");
                    alert.show();
                }
            }
        }
    }

    /**
     * 实现controller的传递，便于在修改信息后刷新
     * @param controler
     */
    public void setParentController(UserManagementController controler) {
        userManagementController = controler;
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage)exitButton.getScene().getWindow();
        stage.close();
    }
}
