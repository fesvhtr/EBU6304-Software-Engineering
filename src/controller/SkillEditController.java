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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SkillEditController implements Initializable
{
    @FXML
    private JFXComboBox<Type> typeComboBox;
    @FXML
    private JFXComboBox<String> sourceTypeComboBox;
    @FXML
    private JFXComboBox<String> sourceComboBox;
    @FXML
    private JFXTextField descriptionField;
    @FXML
    private FontAwesomeIconView exitButton;

    private Skill inSkill;
    private SkillInfoController skillInfoController;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
        List<Type> types = SkillType.getInstance().getTypes();
        for(Type t : types)
        {
            typeObservableList.add(t);
        }
        typeComboBox.setItems(typeObservableList);

        ObservableList<String> sourceTypeObservableList = FXCollections.observableArrayList();
        List<String> sourceTypes = new ArrayList<String>();
        sourceTypes.add("Module");
        sourceTypes.add("Activity");
        for(String t : sourceTypes)
        {
            sourceTypeObservableList.add(t);
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


    @FXML
    void close(MouseEvent event)
    {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    public void setParentController(SkillInfoController controller)
    {
        skillInfoController = controller;
    }

    public void setSkill(Skill skill)
    {
        inSkill = skill;
        typeComboBox.getSelectionModel().select(new Type(skill.getType()));
        sourceTypeComboBox.getSelectionModel().select(skill.getSourceType());
        sourceComboBox.getSelectionModel().select(skill.getSource());
        descriptionField.setText(skill.getDescription());
    }

    @FXML
    void saveHandled(ActionEvent event)
    {
        if (typeComboBox.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("No type is selected");
            alert.show();
            return;
        }
        String type = typeComboBox.getSelectionModel().getSelectedItem().toString();
        String sourceType = sourceTypeComboBox.getSelectionModel().getSelectedItem();
        String source = sourceComboBox.getSelectionModel().getSelectedItem();
        String description = descriptionField.getText();

        if (inSkill != null)
        {
            SkillManager.getInstance().delSkill(inSkill);
        }

        SkillManager.getInstance().addSkill(new Skill(type, sourceType, source, description));
        skillInfoController.initialize(null,null);
        Alert info = new Alert(Alert.AlertType.INFORMATION,"New Skill saved");
        info.showAndWait();
        skillInfoController.initialize(null,null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }
}
