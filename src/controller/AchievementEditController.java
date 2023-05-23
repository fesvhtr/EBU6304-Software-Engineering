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

/**
 * The controller for the achievement edit page.
 */
public class AchievementEditController implements Initializable
{
    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    private JFXComboBox<String> sourceTypeComboBox;
    @FXML
    private JFXComboBox<String> sourceComboBox;

    @FXML
    private JFXTextField descriptionField;

    private Achievement inAchievement;
    private AchievementInfoController achievementInfoController;

    /**
     * Set the achievement Information page.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<String> sourceTypeObservableList = FXCollections.observableArrayList();
        List<String> sources = new ArrayList<>();
        sources.add("Module");
        sources.add("Activity");
        for (String s : sources) {
            sourceTypeObservableList.add(s);
        }
        sourceTypeComboBox.setItems(sourceTypeObservableList);

        sourceTypeComboBox.setOnAction(event ->
        {
            // Clear the items in the sourceComboBox
            sourceComboBox.getItems().clear();

            // Get the selected type from the sourceTypeComboBox
            String selectedType = sourceTypeComboBox.getSelectionModel().getSelectedItem();
            ObservableList<String> sourceObservableList = FXCollections.observableArrayList();
            // Update the sourceComboBox based on the selected type
            if (selectedType != null)
            {
                if (selectedType.toString().equals("Module"))
                {
                    List<Module> modules = ModuleManager.getInstance().getModule();
                    for(Module t : modules)
                    {
                        sourceObservableList.add(t.getName());
                    }
                }
                else if (selectedType.toString().equals("Activity"))
                {
                    List<Activity> activities = ActivityManager.getInstance().getActivities();
                    for(Activity t : activities)
                    {
                        sourceObservableList.add(t.getTitle());
                    }
                }
                sourceComboBox.setItems(sourceObservableList);

            }
        });
    }

    /**
     * Close the window.
     * @param event The mouse event.
     */
    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Set the parent controller.
     * @param controller The parent controller.
     */
    public void setParentController(AchievementInfoController controller) {
        achievementInfoController = controller;
    }

    /**
     * Set the achievement.
     * @param achievement The achievement.
     */
    public void setAchievement(Achievement achievement) {
        inAchievement = achievement;
        descriptionField.setText(achievement.getDescription());
    }

    /**
     * Save the achievement.
     * @param event The mouse event.
     */
    @FXML
    void saveHandled(ActionEvent event) {
        String description = descriptionField.getText();

        if (sourceTypeComboBox.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("No type is selected");
            alert.show();
            return;
        }
        if (sourceComboBox.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("No type is selected");
            alert.show();
            return;
        }
        
        String sourceType = sourceTypeComboBox.getSelectionModel().getSelectedItem();
        String source = sourceComboBox.getSelectionModel().getSelectedItem();
        if (inAchievement != null) {
            AchievementManager.getInstance().delAchievement(inAchievement);
        }
        AchievementManager.getInstance().addAchievement(new Achievement(sourceType, source, description));
        achievementInfoController.initialize(null,null);
        Alert info = new Alert(Alert.AlertType.INFORMATION,"New achievement saved");
        info.showAndWait();
        achievementInfoController.initialize(null,null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

}
