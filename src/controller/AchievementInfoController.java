package controller;

import entity.Achievement;
import entity.AchievementManager;
import entity.ActivityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import view.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The controller for the achievement information page.
 */
public class AchievementInfoController extends InfoController
{
    @FXML
    private TableColumn<Object, String> sourceTypeCol;
    @FXML
    private TableColumn<Object, String> sourceCol;
    @FXML
    private TableColumn<Object, String> descriptionCol;

    private ObservableList<Object> achievementObservableList = FXCollections.observableArrayList();

    /**
     * Set the achievement information page.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        achievementObservableList.clear();

        List<Achievement> achievements = AchievementManager.getInstance().getList();
        for (Achievement a : achievements)
        {
            achievementObservableList.add(a);
        }
        table.setItems(achievementObservableList);
        sourceTypeCol.setCellValueFactory(new PropertyValueFactory<Object, String>("sourceType"));
        sourceCol.setCellValueFactory(new PropertyValueFactory<Object, String>("source"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Object, String>("description"));

        file = "AchievementEdit.fxml";
        manager = AchievementManager.getInstance();
    }

    /**
     * Edit the achievement.
     * @param event The event that the edit button is clicked.
     */
    @FXML
    void editHandled(ActionEvent event)
    {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            Achievement selectedAchievement = (Achievement) table.getSelectionModel().getSelectedItem();
            AchievementEditController controller = (AchievementEditController) ViewManager.newWindow("AchievementEdit.fxml");
            controller.setAchievement(selectedAchievement);
            controller.setParentController(this);
        }
        else
        {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please select an achievement");
            nullWarning.setTitle("No option is selected");
            nullWarning.setHeaderText("No selected option is to be deleted");
            nullWarning.show();
        }
    }

}
