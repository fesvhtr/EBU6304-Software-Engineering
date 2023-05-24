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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import entity.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * The controller for the activity edit page.
 */
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

    /**
     * Judge whether the date is valid.
     * @param dateStr The date string.
     * @return Whether the date is valid.
     */
    private static boolean isValidDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Try again");
            alert.setHeaderText("Date format is not valid, please input in the format of (yyyy-MM-dd).");
            alert.show();
            return false;
        }
    }

    /**
     * Close the activity Information page.
     * @param event The mouse event.
     */
    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Save the activity.
     * @param event The mouse event.
     */
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
        } else if (!isValidDate(start) || !isValidDate(end) ){
            return;
        }


        String type = typeComboBox.getSelectionModel().getSelectedItem().toString();
        if (inActivity != null) {
            ActivityManager.getInstance().removeItem(inActivity);
        }
        ActivityManager.getInstance().addItem(new Activity(title, role, start, end, type));
        activityInfoController.initialize(null,null);
        Alert info = new Alert(Alert.AlertType.INFORMATION,"New Activity saved");
        info.showAndWait();
        activityInfoController.initialize(null,null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Set the parent controller.
     * @param controller The parent controller.
     */
    public void setParentController(ActivityInfoController controller) {
        activityInfoController = controller;
    }

    /**
     * Set the activity.
     * @param activity
     */
    public void setActivity(Activity activity) {
        inActivity = activity;
        titleField.setText(activity.getTitle());
        roleField.setText(activity.getRole());
        startField.setText(activity.getStartDate());
        endField.setText(activity.getEndDate());
        typeComboBox.getSelectionModel().select(new Type(activity.getType()));
    }

    /**
     * Initialize the page.
     * @param location The URL location.
     * @param resources The resource bundle.
     */
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
        List<Type> types = ActivityTypeManager.getInstance().getTypes();
        for(Type t : types){
            typeObservableList.add(t);
        }
        typeComboBox.setItems(typeObservableList);
    }
}
