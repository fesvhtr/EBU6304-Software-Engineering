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

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SkillEditController implements Initializable
{

    @FXML
    private JFXComboBox<Type> typeComboBox;
    @FXML
    private JFXTextField sourceField;
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
        sourceField.setText(skill.getDescription());
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
        String source = sourceField.getText();
        String description = descriptionField.getText();

        if (inSkill != null)
        {
            SkillManager.getInstance().delSkill(inSkill);
        }

        SkillManager.getInstance().addSkill(new Skill(type, source, description));
        skillInfoController.initialize(null,null);
        Alert info = new Alert(Alert.AlertType.INFORMATION,"New Skill saved");
        info.showAndWait();
        skillInfoController.initialize(null,null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }
}
