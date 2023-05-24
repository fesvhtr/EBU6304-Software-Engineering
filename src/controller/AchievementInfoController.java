package controller;

import entity.Achievement;
import entity.AchievementManager;
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
public class AchievementInfoController implements Initializable
{
    @FXML
    private TableView<Achievement> table;
    @FXML
    private TableColumn<Achievement, String> sourceTypeCol;
    @FXML
    private TableColumn<Achievement, String> sourceCol;
    @FXML
    private TableColumn<Achievement, String> descriptionCol;
    private ObservableList<Achievement> achievementObservableList = FXCollections.observableArrayList();

    /**
     * Set the achievement information page.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        achievementObservableList.clear();

        List<Achievement> achievements = AchievementManager.getInstance().getList();
        for (Achievement a : achievements)
        {
            achievementObservableList.add(a);
        }
        table.setItems(achievementObservableList);
        sourceTypeCol.setCellValueFactory(new PropertyValueFactory<Achievement, String>("sourceType"));
        sourceCol.setCellValueFactory(new PropertyValueFactory<Achievement, String>("source"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Achievement, String>("description"));
    }


    /**
     * Add the achievement.
     * @param event The event that the add button is clicked.
     */
    @FXML
    void newHandled(ActionEvent event)
    {
        AchievementEditController controller = (AchievementEditController) ViewManager.newWindow("AchievementEdit.fxml");
        controller.setParentController(this);
    }

    /**
     * Delete the achievement.
     * @param event The event that the delete button is clicked.
     */
    @FXML
    void delHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            Achievement selectedAchievement = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION,"Confirm to delete achievement in " + selectedAchievement.getSourceType() + "?");
            delWarning.setHeaderText("Delete confirmation");
            delWarning.setTitle("Hold on");
            delWarning.showAndWait().ifPresent(response ->
            {
                if (response == ButtonType.OK) {
                    table.getItems().remove(selectedAchievement);
                    AchievementManager.getInstance().removeItem(selectedAchievement);
                    initialize(null, null);
                }
            });
        }
        else
        {
            Alert nullwarning = new Alert(Alert.AlertType.WARNING, "Please select an achievement");
            nullwarning.setTitle("No option is selected");
            nullwarning.setHeaderText("No selected option is to be deleted");
            nullwarning.show();
        }

    }

    /**
     * Edit the achievement.
     * @param event The event that the edit button is clicked.
     */
    @FXML
    void editHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            Achievement selectedAchievement = table.getSelectionModel().getSelectedItem();
            AchievementEditController controller = (AchievementEditController) ViewManager.newWindow("AchievementEdit.fxml");
            controller.setAchievement(selectedAchievement);
            controller.setParentController(this);
        }else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please select an achievement");
            nullWarning.setTitle("No option is selected");
            nullWarning.setHeaderText("No selected option is to be deleted");
            nullWarning.show();
        }
    }

}
