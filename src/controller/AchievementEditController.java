package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.IdGenerator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AchievementEditController implements Initializable
{
    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    private JFXComboBox<String> sourceComboBox;

    @FXML
    private JFXTextField descriptionField;

    private Achievement inAchievement;
    private AchievementInfoController achievementInfoController;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<String> sourceObservableList = FXCollections.observableArrayList();
        List<String> sources = new ArrayList<>();
        sources.add("Module");
        sources.add("Activity");
        for (String s : sources) {
            sourceObservableList.add(s);
        }
        sourceComboBox.setItems(sourceObservableList);
    }

    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    public void setParentController(AchievementInfoController controller) {
        achievementInfoController = controller;
    }

    public void setAchievement(Achievement achievement) {
        inAchievement = achievement;
        descriptionField.setText(achievement.getDescription());
    }

    @FXML
    void saveHandled(ActionEvent event) {
        String description = descriptionField.getText();

        if (sourceComboBox.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("No type is selected");
            alert.show();
            return;
        }
        String source = sourceComboBox.getSelectionModel().getSelectedItem().toString();
        if (inAchievement != null) {
            AchievementManager.getInstance().delAchievement(inAchievement);
        }
        AchievementManager.getInstance().addAchievement(new Achievement(source, description));
        achievementInfoController.initialize(null,null);
        Alert info = new Alert(Alert.AlertType.INFORMATION,"New achievement saved");
        info.showAndWait();
        achievementInfoController.initialize(null,null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

}
