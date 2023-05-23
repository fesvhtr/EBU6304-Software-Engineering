package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import util.FileOperator;

import javax.jws.soap.SOAPBinding;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    public JFXTextField openaiApi;
    public JFXButton myButton;
    public CheckBox needPassword;
    public Slider maxTokens;
    public ChoiceBox gptModel;

    public boolean isNeedPassword;

    @FXML
    public TextField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserConfig userConfig = UserConfigManager.getInstance().getUserConfig();
        this.maxTokens.setValue((double) userConfig.getGptTokens());
        this.needPassword.setSelected(userConfig.isNeedPassword());
        isNeedPassword = userConfig.isNeedPassword();
        this.openaiApi.setText(userConfig.getGptApi());
        this.gptModel.setValue(userConfig.getGptModel());
        if (userConfig.isNeedPassword()){
            this.setPassword();
        }


    }

    public void save(MouseEvent mouseEvent) {
        int token = (int)maxTokens.getValue();
        String api = openaiApi.getText();
        boolean password = needPassword.isSelected();
        String model = (String) gptModel.getValue();
        String passwordText = this.password.getText();

        if (isNeedPassword) {
            List<Student> students = FileOperator.loadData("Student.json", Student.class);
            UserManager userManager = UserManager.getInstance();
            userManager.getCurrentUser().setPassword(passwordText);
            for (Student student : students) {
                if (student.getAccount().equals(userManager.getCurrentUser().getAccount())) {
                    student.setPassword(passwordText);
                }
            }
            FileOperator.writeData(students, "Student.json");
        }

        System.out.println(token);
        System.out.println(api);
        System.out.println(password);
        System.out.println(model);
        UserConfig userConfig = new UserConfig(token, api, model, password);
        UserConfigManager.getInstance().changeUserConfig(userConfig);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Save successfully\n" +
                "token: " + token + "\n" +
                "api: " + api + "\n" +
                "model: " + model + "\n");
        alert.setHeaderText("Save successfully");
        alert.show();
    }

    public void setPassword() {
        boolean isPassword = needPassword.isSelected();
        if (isPassword) {
            password.setVisible(true);
            password.setDisable(false);
            password.setText(UserManager.getInstance().getCurrentUser().getPassword());
        }
        else {
            password.setVisible(false);
            password.setDisable(true);
        }
    }


}
