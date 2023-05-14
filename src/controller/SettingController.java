package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.UserConfig;
import entity.UserConfigManager;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

import javax.jws.soap.SOAPBinding;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    public JFXTextField openaiApi;
    public JFXButton myButton;
    public CheckBox needPassword;
    public Slider maxTokens;
    public ChoiceBox gptModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserConfig userConfig = UserConfigManager.getInstance().getUserConfig();
        this.maxTokens.setValue((double) userConfig.getGptTokens());
        this.needPassword.setSelected(userConfig.isNeedPassword());
        this.openaiApi.setText(userConfig.getGptApi());
        this.gptModel.setValue(userConfig.getGptModel());
    }

    public void save(MouseEvent mouseEvent) {
        int token = (int)maxTokens.getValue();
        String api = openaiApi.getText();
        boolean password = needPassword.isSelected();
        String model = (String) gptModel.getValue();
        System.out.println(token);
        System.out.println(api);
        System.out.println(password);
        System.out.println(model);
        UserConfig userConfig = new UserConfig(token, api, model, password);
        UserConfigManager.getInstance().changeUserConfig(userConfig);
    }
}
