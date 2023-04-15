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

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-20 1:20
 */
public class LoginController implements Initializable {
    @FXML
    private JFXTextField accountTextFiield;

    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private ToggleGroup roleGroup;
    @FXML
    RadioButton retailerButton;
    @FXML
    RadioButton factoryAdminButton;
    @FXML
    RadioButton superAdminButton;
    @FXML
    private FontAwesomeIconView exitButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleGroup = new ToggleGroup();
        retailerButton.setToggleGroup(roleGroup);
        factoryAdminButton.setToggleGroup(roleGroup);
        superAdminButton.setToggleGroup(roleGroup);
        superAdminButton.setOnAction(e -> {
            accountTextFiield.setText("888");
            passwordField.setText("888");
        });
        factoryAdminButton.setOnAction(e -> {
            accountTextFiield.setText("123");
            passwordField.setText("123");
        });
        retailerButton.setOnAction(e -> {
            accountTextFiield.setText("321");
            passwordField.setText("321");
        });
    }

    @FXML
    private void loginHandled() {
        String account = accountTextFiield.getText();
        String password = passwordField.getText();
        if (account.equals("") && password.equals("")) {
        } else if (account.indexOf(" ") >= 0 || password.indexOf(" ") >= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧");
            alert.setHeaderText("用户名和密码都不能包含空格“ ”");
            alert.show();
        } else if (roleGroup.getSelectedToggle() == retailerButton && UserManager.getInstance().CheckLogin(account, password, 1)) {
            ViewManager.newWindow("RetailerMenu.fxml");
            System.out.println("零售商"+account+"已登录");
        } else if (roleGroup.getSelectedToggle() == factoryAdminButton && UserManager.getInstance().CheckLogin(account, password, 2)) {
            ViewManager.newWindow("FactoryAdminMenu.fxml");
            System.out.println("云厂长"+account+"已登录");
        } else if (roleGroup.getSelectedToggle() == superAdminButton && UserManager.getInstance().CheckLogin(account, password, 3)) {
            ViewManager.newWindow("StudentMenu.fxml");
            System.out.println("student"+account+"log in");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧\n注：\n经销商账号321、654、987\n云厂主账号123、456、789\n超级管理员账号888\n所有密码同账号");
            alert.setHeaderText("账号或密码错误");
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
        ViewManager.newWindow("Register.fxml");
    }
}
