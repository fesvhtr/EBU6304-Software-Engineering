package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.UserConfig;
import entity.UserConfigManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for the setting page.
 */
public class SettingController implements Initializable {
    public JFXTextField openaiApi;
    public JFXButton myButton;
    public CheckBox needPassword;
    public Slider maxTokens;
    public ChoiceBox gptModel;

    public boolean isNeedPassword;

    @FXML
    public TextField password;

    /**
     * Initialize the setting page.
     *
     * @param location  The location.
     * @param resources The resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserConfig userConfig = UserConfigManager.getInstance().getUserConfig();
        this.maxTokens.setValue((double) userConfig.getGptTokens());
        this.needPassword.setSelected(userConfig.isNeedPassword());
        isNeedPassword = userConfig.isNeedPassword();
        this.openaiApi.setText(userConfig.getGptApi());
        this.gptModel.setValue(userConfig.getGptModel());
        if (userConfig.isNeedPassword()) {
            this.setPassword();
        }
    }

    /**
     * Save the setting.
     *
     * @param mouseEvent The mouse event.
     */
    public void save(MouseEvent mouseEvent) {
        int token = (int) maxTokens.getValue();
        String api = openaiApi.getText();
        boolean needPasswordSelected = needPassword.isSelected();
        String model = (String) gptModel.getValue();
        String passwordText = this.password.getText();
        if (isNeedPassword) {
            if (passwordText.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Password cannot be empty");
                alert.setHeaderText("Password cannot be empty");
                alert.show();
                return;
            }
        }
        UserConfig userConfig = new UserConfig(token, api, model, needPasswordSelected, passwordText);
        UserConfigManager.getInstance().changeUserConfig(userConfig);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Save successfully\n" +
                "token: " + token + "\n" +
                "api: " + api + "\n" +
                "model: " + model + "\n");
        alert.setHeaderText("Save successfully");
        alert.show();
    }

    /**
     * Set the password.
     */
    public void setPassword() {
        boolean isPassword = needPassword.isSelected();
        if (isPassword) {
            password.setVisible(true);
            password.setDisable(false);
            password.setText(UserConfigManager.getInstance().getUserConfig().getPassword());
        } else {
            password.setVisible(false);
            password.setDisable(true);
        }
    }

}
