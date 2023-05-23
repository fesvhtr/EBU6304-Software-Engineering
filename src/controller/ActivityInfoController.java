package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entity.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.ViewManager;



import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.util.Callback;

import javafx.event.EventHandler;



public class ActivityInfoController implements Initializable {
    @FXML
    private JFXListView<String> list;

    @FXML
    private TableView<Activity> table;

    @FXML
    private TableColumn<Activity, String> acCol;

    @FXML
    private TableColumn<Activity, String> roleCol;

    @FXML
    private TableColumn<Activity, String> starCol;

    @FXML
    private TableColumn<Activity, String> endCol;


    @FXML
    private Label title;

    @FXML
    private JFXTextField newTypeField;

    @FXML
    private JFXButton configureButton;

    @FXML
    void delTypeHandled(ActionEvent event) {
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Type selectedType = new Type (list.getSelectionModel().getSelectedItem());
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION, "You want to delete " + selectedType + "?");
            delWarning.setHeaderText("Delete confirm");
            delWarning.setTitle("Wait...");
            delWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    ActivityType.getInstance().removeType(selectedType);
                    System.out.println(1);
                    initialize(null, null);
                    System.out.println(2);
                }
            });
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please check one item in the table");
            nullWarning.setTitle("Hint: No item is selected!");
            nullWarning.setHeaderText("No item is selected!");
            nullWarning.show();
        }
    }

    @FXML
    public void newTypeHandled(ActionEvent event) {
        newTypeField.setVisible(true);
        configureButton.setVisible(true);

    }

    @FXML
    void configureHandled(ActionEvent event) {
        if (newTypeField.getText().equals("")) return;
        if (title.getText().equals("Activity Information Management")) {
            ActivityType.getInstance().addType(newTypeField.getText());
        } else if (title.getText().equals("Product Type Management")) {
            ModuleType.getInstance().addType(newTypeField.getText());
        }
        initialize(null, null);
        newTypeField.setText("");
        newTypeField.setVisible(false);
        configureButton.setVisible(false);
    }


    private ObservableList<String> activityTypeObservableList = FXCollections.observableArrayList();

    private ObservableList<Activity> activityObservableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        activityTypeObservableList.clear();
        List<Type> activityTypes = ActivityType.getInstance().getTypes();
        for (Type t : activityTypes) {

            activityTypeObservableList.add(t.toString());
        }
        title.setText("Activity Information Management");


        list.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                    }
                };

                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (!cell.isEmpty()) {
                            String selectedItem = cell.getItem();
                            refreshTable(selectedItem);
                        }
                    }
                });

                return cell;
            }
        });
        list.setItems(activityTypeObservableList);

        list.getSelectionModel().selectFirst();



        acCol.setCellValueFactory(new PropertyValueFactory<Activity, String>("title"));
        roleCol.setCellValueFactory(new PropertyValueFactory<Activity, String>("role"));
        starCol.setCellValueFactory(new PropertyValueFactory<Activity, String>("startDate"));
        endCol.setCellValueFactory(new PropertyValueFactory<Activity, String>("endDate"));
    }

    @FXML
    void delActivityHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Activity selectedActivity = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION,"It will delete your activity " + selectedActivity.getTitle() + " from " + selectedActivity.getStartDate() + " to " + selectedActivity.getEndDate());
            delWarning.setHeaderText("Are you sure?");
            delWarning.setTitle("Warning");
            delWarning.showAndWait().ifPresent(response ->{
                if (response == ButtonType.OK) {
                    table.getItems().remove(selectedActivity);
                    ActivityManager.getInstance().delActivity(selectedActivity);
                    initialize(null, null);
                }
            });
        } else {
            Alert nullwarning = new Alert(Alert.AlertType.WARNING, "Please select item from the table.");
            nullwarning.setTitle("ATTENTION: No item");
            nullwarning.setHeaderText("No item has benn selected.");
            nullwarning.show();
        }

    }

    public void refreshTable(String selectedType) {
        activityObservableList.clear();
        List<Activity> activities = ActivityManager.getInstance().getActivities();
        for (Activity a : activities){
            if(a.getType().equals(selectedType)){
                activityObservableList.add(a);
            }
        }
        table.setItems(activityObservableList);
    }

    public void newAcHandled(ActionEvent event) {
        ActivityEditController controller = (ActivityEditController) ViewManager.newWindow("ActivityEdit.fxml");
        controller.setParentController(this);
    }

    @FXML
    void editAcHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Activity selectedActivity = table.getSelectionModel().getSelectedItem();
            ActivityEditController controller = (ActivityEditController) ViewManager.newWindow("ActivityEdit.fxml");
            controller.setActivity(selectedActivity);
            controller.setParentController(this);
        }else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please select item from the table.");
            nullWarning.setTitle("ATTENTION: No item");
            nullWarning.setHeaderText("No item has benn selected.");
            nullWarning.show();
        }
    }
}