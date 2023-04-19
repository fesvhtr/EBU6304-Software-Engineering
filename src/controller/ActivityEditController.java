package controller;

import entity.*;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import entity.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;


public class ActivityEditController implements Initializable {

    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    private JFXTextField startField;

    @FXML
    private JFXTextField endField;

    @FXML
    private JFXTextField roleField;

    @FXML
    private JFXComboBox<Type> typeComboBox;

    @FXML
    private JFXTextField titleField;

    private Activity inActivity;
    private ActivityInfoController activityInfoController;

    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void saveHandled(ActionEvent event) {
        String title = titleField.getText();
        String role = roleField.getText();
        String end = endField.getText();
        String start = startField.getText();

        if (title.equals("") || role.equals("") || end.equals("") || start.equals("") ) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("Can't be null");
            alert.show();
            return;
        } else if (typeComboBox.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("No type is selected");
            alert.show();
            return;
        }
        String type = typeComboBox.getSelectionModel().getSelectedItem().toString();
        if (inActivity != null) {
            ActivityManager.getInstance().delActivity(inActivity);
        }
        ActivityManager.getInstance().addActivity(new Activity(title, role, start, end, type));
        activityInfoController.initialize(null,null);
        Alert info = new Alert(Alert.AlertType.INFORMATION,"New Activity saved");
        info.showAndWait();
        activityInfoController.initialize(null,null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    public void setParentController(ActivityInfoController controller) {
        activityInfoController = controller;
    }

    public void setProduct(Activity activity) {
        inActivity = activity;
        titleField.setText(activity.getTitle());
        roleField.setText(activity.getRole());
        startField.setText(activity.getStartDate());
        endField.setText(activity.getEndDate());
        typeComboBox.getSelectionModel().select(new Type(activity.getType()));
    }


    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
        List<Type> types = ActivityType.getInstance().getTypes();
        for(Type t : types){
            typeObservableList.add(t);
        }
        typeComboBox.setItems(typeObservableList);
    }
}
