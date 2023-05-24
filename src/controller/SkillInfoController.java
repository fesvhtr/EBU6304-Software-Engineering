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

/**
 * The controller for the skill information page.
 */
public class SkillInfoController extends InfoController
{
    @FXML
    private JFXListView<String> list;
    @FXML
    private TableColumn<Object, String> typeCol;
    @FXML
    private TableColumn<Object, String> sourceTypeCol;
    @FXML
    private TableColumn<Object, String> sourceCol;
    @FXML
    private TableColumn<Object, String> descriptionCol;

    @FXML
    private Label title;

    @FXML
    private JFXTextField newTypeField;
    @FXML
    private JFXButton confirmButton;

    private ObservableList<String> skillTypeObservableList = FXCollections.observableArrayList();
    private ObservableList<Object> skillObservableList = FXCollections.observableArrayList();

    /**
     * Initialize the skill information page.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        skillTypeObservableList.clear();
        List<Type> skillTypes = SkillTypeManager.getInstance().getList();
        for (Type t : skillTypes)
        {
            skillTypeObservableList.add(t.toString());
        }
        title.setText("Skill Information Management");


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
        list.setItems(skillTypeObservableList);

        list.getSelectionModel().selectFirst();

        typeCol.setCellValueFactory(new PropertyValueFactory<Object, String>("type"));
        sourceTypeCol.setCellValueFactory(new PropertyValueFactory<Object, String>("sourceType"));
        sourceCol.setCellValueFactory(new PropertyValueFactory<Object, String>("source"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Object, String>("description"));

        file = "SkillEdit.fxml";
        manager = SkillManager.getInstance();
    }

    /**
     * Add a new skill.
     * @param event The event that the button is clicked.
     */
    @FXML
    void newHandled(ActionEvent event)
    {
        SkillEditController controller = (SkillEditController) ViewManager.newWindow("SkillEdit.fxml");
        controller.setParentController(this);
    }

    /**
     * Delete a skill.
     * @param event The event that the button is clicked.
     */


    /**
     * Edit a skill.
     * @param event The event that the button is clicked.
     */
    @FXML
    void editHandled(ActionEvent event)
    {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            Skill selectedSkill = (Skill) table.getSelectionModel().getSelectedItem();
            SkillEditController controller = (SkillEditController) ViewManager.newWindow("SkillEdit.fxml");
            controller.setSkill(selectedSkill);
            controller.setParentController(this);
        }
        else
        {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please select a skill");
            nullWarning.setTitle("Warning: no selection");
            nullWarning.setHeaderText("No skill is selected to be edited");
            nullWarning.show();
        }
    }

    /**
     * Refresh the table.
     * @param selectedType The selected type.
     */
    public void refreshTable(String selectedType)
    {
        skillObservableList.clear();
        List<Skill> skills = SkillManager.getInstance().getList();
        for (Skill a : skills)
        {
            if(a.getType().equals(selectedType))
            {
                skillObservableList.add(a);
            }
        }
        table.setItems(skillObservableList);
    }

    /**
     * Add a new type.
     * @param event The event that the button is clicked.
     */
    @FXML
    public void newTypeHandled(ActionEvent event)
    {
        newTypeField.setVisible(true);
        confirmButton.setVisible(true);
    }

    /**
     * Delete a type.
     * @param event The event that the button is clicked.
     */
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
                    SkillTypeManager.getInstance().removeItem(selectedType);
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

    /**
     * Confirm the new type.
     * @param event The event that the button is clicked.
     */
    @FXML
    void confirmHandled(ActionEvent event)
    {
        if (newTypeField.getText().equals("")) return;

        if (title.getText().equals("Skill Information Management"))
        {
            SkillTypeManager.getInstance().removeItem(newTypeField.getText());
        }

        initialize(null, null);
        newTypeField.setText("");
        newTypeField.setVisible(false);
        confirmButton.setVisible(false);
    }
}