package controller;

import entity.Achievement;
import entity.AchievementManager;
import entity.Module;
import entity.ModuleManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.ViewManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AchievementInfoController implements Initializable
{
    @FXML
    private TableView<Achievement> table;
    @FXML
    private TableColumn<Achievement, String> sourceCol;
    @FXML
    private TableColumn<Achievement, String> descriptionCol;
    private ObservableList<Achievement> achievementObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        achievementObservableList.clear();
        List<Achievement> modules = AchievementManager.getInstance().getAchievements();
        for (Achievement a : modules) {
            achievementObservableList.add(a);
        }
        table.setItems(achievementObservableList);
        sourceCol.setCellValueFactory(new PropertyValueFactory<Achievement, String>("credit"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Achievement, String>("description"));
    }


    @FXML
    void newHandled(ActionEvent event)
    {
        AchievementEditController controller = (AchievementEditController) ViewManager.newWindow("AchievementEdit.fxml");
        controller.setParentController(this);
    }

    @FXML
    void delHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Achievement selectedAchievement = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION,"Confirm to delete achievement in " + selectedAchievement.getSource() + "?");
            delWarning.setHeaderText("Delete confirmation");
            delWarning.setTitle("Hold on");
            delWarning.showAndWait().ifPresent(response ->
            {
                if (response == ButtonType.OK) {
                    table.getItems().remove(selectedAchievement);
                    AchievementManager.getInstance().delAchievement(selectedAchievement);
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

    @FXML
    void editHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
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
