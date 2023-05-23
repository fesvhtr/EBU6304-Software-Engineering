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

/**
 * The controller for the skill information page.
 */
public class SkillInfoController implements Initializable {
    @FXML
    private JFXListView<String> list;

    @FXML
    private TableView<Skill> table;

    @FXML
    private TableColumn<Skill, String> typeCol;

    @FXML
    private TableColumn<Skill, String> sourceTypeCol;
    @FXML
    private TableColumn<Skill, String> sourceCol;

    @FXML
    private TableColumn<Skill, String> descriptionCol;

    @FXML
    private Label title;

    @FXML
    private JFXTextField newTypeField;

    @FXML
    private JFXButton confirmButton;

    private ObservableList<String> skillTypeObservableList = FXCollections.observableArrayList();

    private ObservableList<Skill> skillObservableList = FXCollections.observableArrayList();

    /**
     * Initialize the skill information page.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        skillTypeObservableList.clear();
        List<Type> skillTypes = SkillType.getInstance().getTypes();
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

        typeCol.setCellValueFactory(new PropertyValueFactory<Skill, String>("type"));
        sourceTypeCol.setCellValueFactory(new PropertyValueFactory<Skill, String>("sourceType"));
        sourceCol.setCellValueFactory(new PropertyValueFactory<Skill, String>("source"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Skill, String>("description"));
    }

    /**
     * Refresh the table.
     * @param selectedType The selected type.
     */
    public void refreshTable(String selectedType)
    {
        skillObservableList.clear();
        List<Skill> skills = SkillManager.getInstance().getSkills();
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
                    SkillType.getInstance().removeType(selectedType);
                    System.out.println(1);
                    initialize(null, null);
                    System.out.println(2);
                }
            });
        } else
        {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please check one item in the table");
            nullWarning.setTitle("Hint: No item is selected!");
            nullWarning.setHeaderText("No item is selected!");
            nullWarning.show();
        }
    }


    @FXML
    void confirmHandled(ActionEvent event)
    {
        if (newTypeField.getText().equals("")) return;

        if (title.getText().equals("Skill Information Management"))
        {
            SkillType.getInstance().addType(newTypeField.getText());
        }

        initialize(null, null);
        newTypeField.setText("");
        newTypeField.setVisible(false);
        confirmButton.setVisible(false);
    }

    /**
     * Add a new skill.
     * @param event The event that the button is clicked.
     */
    @FXML
    public void newSkillHandled(ActionEvent event)
    {
        SkillEditController controller = (SkillEditController) ViewManager.newWindow("SkillEdit.fxml");
        controller.setParentController(this);
    }

    /**
     * Delete a skill.
     * @param event The event that the button is clicked.
     */
    @FXML
    void delSkillHandled(ActionEvent event)
    {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Skill selectedSkill = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION,"It will delete your skill " + selectedSkill.getType() + "in " +  selectedSkill.getSourceType());
            delWarning.setHeaderText("Are you sure?");
            delWarning.setTitle("Warning");
            delWarning.showAndWait().ifPresent(response ->
            {
                if (response == ButtonType.OK) {
                    table.getItems().remove(selectedSkill);
                    SkillManager.getInstance().delSkill(selectedSkill);
                    initialize(null, null);
                }
            });
        }
        else
        {
            Alert nullwarning = new Alert(Alert.AlertType.WARNING, "Please select a skill");
            nullwarning.setTitle("Warning: no selection");
            nullwarning.setHeaderText("No skill is selected to be deleted");
            nullwarning.show();
        }

    }

    /**
     * Edit a skill.
     * @param event The event that the button is clicked.
     */
    @FXML
    void editSkillHandled(ActionEvent event)
    {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            Skill selectedSkill = table.getSelectionModel().getSelectedItem();
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
}