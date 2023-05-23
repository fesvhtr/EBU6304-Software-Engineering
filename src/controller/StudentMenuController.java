package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.ViewManager;

import java.awt.*;


public class StudentMenuController {

    public JFXButton SettingButton;
    @FXML
    private BorderPane rootLayout;

    @FXML
    private JFXTextField textField;

    @FXML
    private JFXButton deviceManageButton;

    @FXML
    private JFXButton OrderInfoButton;

    @FXML
    private JFXButton userManagementButton;

    @FXML
    private JFXButton factoryInfoButton;

    @FXML
    private JFXButton achievementInfoButton;

    @FXML
    private JFXButton productTypeButton;

    @FXML
    private JFXButton productInfoButton;
    @FXML
    private JFXButton timeMachineButton;
    @FXML
    private JFXButton portfolioButton;

    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void showPortfolios(ActionEvent event){rootLayout.setCenter(ViewManager.getPane("Portfolio.fxml"));}

    @FXML
    void showTimeMachine(ActionEvent event){rootLayout.setCenter(ViewManager.getPane("TimeMachine.fxml"));}

    @FXML
    void showDeviceManagement(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("RoleManagement.fxml"));
    }

    @FXML
    void showDeviceTypeManagement(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("ActivityInfo.fxml"));
    }

    @FXML
    void showFactoryInfo(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("SkillInfo.fxml"));
    }

    @FXML
    void showAchievementInfo(ActionEvent event)
    {
        rootLayout.setCenter(ViewManager.getPane("AchievementInfo.fxml"));
    }

    @FXML
    void showOrderInfo(ActionEvent event) {

    }

    @FXML
    void showProductInfo(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("ModuleInfo.fxml"));
    }

    @FXML
    void showProductType(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("ModuleTypeManagement.fxml"));
    }

    @FXML
    void showUserManagement(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("UserManagement.fxml"));
    }

    @FXML
    void showSetting(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("Setting.fxml"));
    }

    @FXML
    private TextArea gptTextArea;

    public void search(MouseEvent mouseEvent) {
        System.out.println(textField.getText());
        System.out.println(GPTController.generateText(textField.getText()));
        gptTextArea.setVisible(true);
        gptTextArea.setText("Response: " + GPTController.generateText(textField.getText()));
        Duration visibilityDuration = Duration.seconds(10); // Set the duration of visibility (5 seconds)
        Timeline timeline = new Timeline(new KeyFrame(visibilityDuration, event -> {
            gptTextArea.setVisible(false); // Set visibility to false after the duration
        }));
        timeline.play();

    }
}
