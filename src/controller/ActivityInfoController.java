package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entity.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.ViewManager;



import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.util.Callback;

import javafx.event.EventHandler;



public class ActivityInfoController extends InfoController
{
    @FXML
    private JFXListView<String> list;

    @FXML
    private TableColumn<Object, String> acCol;
    @FXML
    private TableColumn<Object, String> roleCol;
    @FXML
    private TableColumn<Object, String> starCol;
    @FXML
    private TableColumn<Object, String> endCol;


    @FXML
    private Label title;
    @FXML
    private JFXTextField newTypeField;
    @FXML
    private JFXButton configureButton;


    private ObservableList<String> activityTypeObservableList = FXCollections.observableArrayList();
    private ObservableList<Object> activityObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        activityTypeObservableList.clear();
        List<Type> activityTypes = ActivityTypeManager.getInstance().getList();
        for (Type t : activityTypes) {

            activityTypeObservableList.add(t.toString());
        }
        title.setText("Activity Information Management");


        list.setCellFactory(new Callback<ListView<String>, ListCell<String>>()
        {
            @Override
            public ListCell<String> call(ListView<String> listView)
            {
                ListCell<String> cell = new ListCell<String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        setText(item);
                    }
                };

                cell.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        if (!cell.isEmpty())
                        {
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

        acCol.setCellValueFactory(new PropertyValueFactory<Object, String>("title"));
        roleCol.setCellValueFactory(new PropertyValueFactory<Object, String>("role"));
        starCol.setCellValueFactory(new PropertyValueFactory<Object, String>("startDate"));
        endCol.setCellValueFactory(new PropertyValueFactory<Object, String>("endDate"));

        file = "ActivityEdit.fxml";
        manager = ActivityManager.getInstance();
    }

    public void abstractEditHandled(ActionEvent event)
    {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            Activity selectedActivity = (Activity) table.getSelectionModel().getSelectedItem();
            ActivityEditController controller = (ActivityEditController) ViewManager.newWindow("ActivityEdit.fxml");
            controller.setActivity(selectedActivity);
            controller.setParentController(this);
        }
        else
        {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please select item from the table.");
            nullWarning.setTitle("ATTENTION: No item");
            nullWarning.setHeaderText("No item has benn selected.");
            nullWarning.show();
        }
    }

    public void refreshTable(String selectedType)
    {
        activityObservableList.clear();
        List<Activity> activities = ActivityManager.getInstance().getList();
        for (Activity a : activities)
        {
            if(a.getType().equals(selectedType))
            {
                activityObservableList.add(a);
            }
        }
        System.out.println(activityObservableList);
        table.setItems(activityObservableList);
    }

    @FXML
    public void newTypeHandled(ActionEvent event)
    {
        newTypeField.setVisible(true);
        configureButton.setVisible(true);
    }

    @FXML
    void delTypeHandled(ActionEvent event)
    {
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            Type selectedType = new Type (list.getSelectionModel().getSelectedItem());
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION, "You want to delete " + selectedType + "?");
            delWarning.setHeaderText("Delete confirm");
            delWarning.setTitle("Wait...");
            delWarning.showAndWait().ifPresent(response ->
            {
                if (response == ButtonType.OK)
                {
                    ActivityTypeManager.getInstance().removeItem(selectedType);
                    System.out.println(1);
                    initialize(null, null);
                    System.out.println(2);
                }
            });
        }
        else
        {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please check one item in the table");
            nullWarning.setTitle("Hint: No item is selected!");
            nullWarning.setHeaderText("No item is selected!");
            nullWarning.show();
        }
    }

    @FXML
    void configureHandled(ActionEvent event) {
        if (newTypeField.getText().equals("")) return;
        if (title.getText().equals("Activity Information Management")) {
            ActivityTypeManager.getInstance().addItem(newTypeField.getText());
        } else if (title.getText().equals("Product Type Management")) {
            ModuleTypeManager.getInstance().addItem(newTypeField.getText());
        }
        initialize(null, null);
        newTypeField.setText("");
        newTypeField.setVisible(false);
        configureButton.setVisible(false);
    }
}