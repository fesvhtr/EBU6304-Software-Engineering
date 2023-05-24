package controller;

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

/**
 * The controller for the module information page.
 */
public class ModuleInfoController implements Initializable {
    @FXML
    private TableView<Module> table;

    @FXML
    private TableColumn<Module, String> idCol;

    @FXML
    private TableColumn<Module, String> nameCol;

    @FXML
    private TableColumn<Module, String> typeCol;
    @FXML
    private TableColumn<Module, String> creditCol;
    @FXML
    private TableColumn<Module, String> markCol;

    @FXML
    private TableColumn<Module, String> descriptionCol;

    private ObservableList<Module> moduleObservableList = FXCollections.observableArrayList();

    private ArrayList<Object> my_eva = new ArrayList<>();

    /**
     * Initialize the module information page.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moduleObservableList.clear();
        List<Module> modules = ModuleManager.getInstance().getList();
        for (Module p : modules) {
            moduleObservableList.add(p);
        }
        table.setItems(moduleObservableList);
        idCol.setCellValueFactory(new PropertyValueFactory<Module, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Module, String>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Module, String>("type"));
        markCol.setCellValueFactory(new PropertyValueFactory<Module, String>("mark"));
        creditCol.setCellValueFactory(new PropertyValueFactory<Module, String>("credit"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Module, String>("description"));

        //test eva func
        System.out.println(ModuleManager.getInstance().getEva().toString());
        my_eva = ModuleManager.getInstance().getEva();

    }

    /**
     * Delete the module.
     * @param event The event that the delete button is clicked.
     */
    @FXML
    void delHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Module selectedModule = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION,"Confirm to delete " + selectedModule.getName() + "?");
            delWarning.setHeaderText("delete confirmed");
            delWarning.setTitle("Hold on..");
            delWarning.showAndWait().ifPresent(response ->{
                if (response == ButtonType.OK) {
                    table.getItems().remove(selectedModule);
                    ModuleManager.getInstance().removeItem(selectedModule);
                    initialize(null, null);
                }
            });
        } else {
            Alert nullwarning = new Alert(Alert.AlertType.WARNING, "Please select a module");
            nullwarning.setTitle("No item selected");
            nullwarning.setHeaderText("No module to delete");
            nullwarning.show();
        }

    }

    /**
     * Edit the module.
     * @param event The event that the edit button is clicked.
     */
    @FXML
    void editHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Module selectedModule = table.getSelectionModel().getSelectedItem();
            ModuleEditController controller = (ModuleEditController) ViewManager.newWindow("ModuleEdit.fxml");
            controller.setModule(selectedModule);
            controller.setParentController(this);
        }else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please select a module");
            nullWarning.setTitle("No item selected");
            nullWarning.setHeaderText("No module to edit");
            nullWarning.show();
        }
    }

    /**
     * Add a new module.
     * @param event The event that the add button is clicked.
     */
    @FXML
    void newHandled(ActionEvent event) {
        ModuleEditController controller = (ModuleEditController) ViewManager.newWindow("ModuleEdit.fxml");
        controller.setParentController(this);
    }

    /**
     * Show the mark report.
     * @param event The event that the GPA button is clicked.
     */
    @FXML
    void GPAHandled(ActionEvent event) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION, "Here is your Mark Report");
        infoAlert.setTitle("Mark Report");
        infoAlert.setHeaderText("Yor GPA (out of 100 scale) is " + my_eva.get(0) +"\nYor GPA (out of 4.0 scale) is "
                + my_eva.get(1)+ "\nYor Degree level is " + my_eva.get(2));
        infoAlert.show();
    }

    public ArrayList<Object> getEva() {
        ArrayList<Object> eva = new ArrayList<>();
        String level;
        float weightedMarks = 0;
        float totalCredits = 0;
        float weightedGradePoints = 0;
        //TODO more kinds of gpa cal algorithm, and may need consider module type
        //TODO is rank need?
        for (Module module : modules) {
            float mark = module.getMark();
            float credit = module.getCredit();
            weightedMarks += mark * credit;
            totalCredits += credit;
            if (mark >= 60 && mark <= 100) {
                float gradePoints = (float) (4.0 - 3.0 * (100 - mark) * (100 - mark) / 1600);
                weightedGradePoints += gradePoints * credit;
            }
        }

        float average = weightedMarks / totalCredits;
        float GPA = weightedGradePoints / totalCredits;
        if (average >= 70.0)
            level = "First class";
        else if (average >= 60.0)
            level = "Upper second class";
        else if (average >= 50.0)
            level = "Lower second class";
        else if (average >= 40.0)
            level = "Third  class";
        else
            level = "Pass";

        eva.add(average);
        eva.add(GPA);
        eva.add(level);

        return eva;
    }
}
