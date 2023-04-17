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
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-23 2:42
 */
public class DeviceTypeManagementController implements Initializable {
    @FXML
    private JFXListView<Type> list;

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
            Type selectedType = list.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION, "You want to delete" + selectedType + "?");
            delWarning.setHeaderText("Delete confirm");
            delWarning.setTitle("Wait...");
            delWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    list.getItems().remove(selectedType);
                    DeviceType.getInstance().removeType(selectedType);
                    initialize(null, null);
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
    void newTypeHandled(ActionEvent event) {
        newTypeField.setVisible(true);
        configureButton.setVisible(true);

    }

    @FXML
    void configureHandled(ActionEvent event) {
        if (newTypeField.getText().equals("")) return;
        if (title.getText().equals("Device Type Management")) {
            DeviceType.getInstance().addType(newTypeField.getText());
        } else if (title.getText().equals("Product Type Management")) {
            ModuleType.getInstance().addType(newTypeField.getText());
        }
        initialize(null, null);
        newTypeField.setText("");
        newTypeField.setVisible(false);
        configureButton.setVisible(false);
    }

    private ObservableList<Type> deviceTypeObservableList = FXCollections.observableArrayList();

    private ObservableList<Activity> activityObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deviceTypeObservableList.clear();
        List<Type> deviceTypes = DeviceType.getInstance().getTypes();
        for (Type t : deviceTypes) {
            deviceTypeObservableList.add(t);
        }
        title.setText("Activity Management");
        list.setItems(deviceTypeObservableList);

        activityObservableList.clear();
        List<Activity> activities = ActivityManager.getInstance().getActivities();
        for (Activity a : activities) {
            //select
            activityObservableList.add(a);
        }
        table.setItems(activityObservableList);
        acCol.setCellValueFactory(new PropertyValueFactory<Activity, String>("title"));
        roleCol.setCellValueFactory(new PropertyValueFactory<Activity, String>("role"));
        starCol.setCellValueFactory(new PropertyValueFactory<Activity, String>("startDate"));
        endCol.setCellValueFactory(new PropertyValueFactory<Activity, String>("endDate"));




    }

        @FXML

//    void selectTypeHandled(MouseEvent event) {
//        System.out.println(1);
//        int selectedIndex = list.getSelectionModel().getSelectedIndex();
//        if (selectedIndex >= 0) {
//            Type selectedType = list.getSelectionModel().getSelectedItem();
//            activityObservableList.clear();
//            List<Activity> activities = ActivityManager.getInstance().getActivities();
//            for (Activity a : activities) {
//                //select
//                if (a.getType().equals(selectedType)) {
//                    activityObservableList.add(a);
//                }
//            }
//            table.setItems(activityObservableList);
//            acCol.setCellValueFactory(new PropertyValueFactory<Activity, String>("title"));
//            roleCol.setCellValueFactory(new PropertyValueFactory<Activity, String>("role"));
//            starCol.setCellValueFactory(new PropertyValueFactory<Activity, String>("startDate"));
//            endCol.setCellValueFactory(new PropertyValueFactory<Activity, String>("endDate"));
////            table.refresh();
////        }
//    }

        void delActivityHandled (ActionEvent event){
            int selectedIndex = table.getSelectionModel().getSelectedIndex();
        }
    }