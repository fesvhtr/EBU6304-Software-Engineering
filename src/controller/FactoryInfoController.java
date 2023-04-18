package controller;

import com.jfoenix.controls.JFXButton;
import entity.FactoryAdmin;
import entity.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class FactoryInfoController implements Initializable {
    @FXML
    private TableView<FactoryAdmin> table;
    @FXML
    private TableColumn<FactoryAdmin, String> factoryNameCol;

    @FXML
    private TableColumn<FactoryAdmin, String> factoryDescriptionCol;

    @FXML
    private TableColumn<FactoryAdmin, String> nameCol;

    @FXML
    private TableColumn<FactoryAdmin, String> contactCol;

    @FXML
    private TableColumn<FactoryAdmin, String> accountCol;

    @FXML
    private TableColumn<FactoryAdmin, String> statusCol;

    @FXML
    private JFXButton stopButton;

    @FXML
    private JFXButton launchButton;

    private ObservableList<FactoryAdmin> factoryAdminObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        factoryAdminObservableList.clear();
        List<FactoryAdmin> factoryAdmins = UserManager.getInstance().getFactoryAdmins();
        for (FactoryAdmin fa : factoryAdmins) {
            factoryAdminObservableList.add(fa);
        }
        table.setItems(factoryAdminObservableList);
        factoryNameCol.setCellValueFactory(new PropertyValueFactory<FactoryAdmin, String>("factoryName"));
        factoryDescriptionCol.setCellValueFactory(new PropertyValueFactory<FactoryAdmin, String>("factoryDescription"));
        nameCol.setCellValueFactory(new PropertyValueFactory<FactoryAdmin, String>("name"));
        contactCol.setCellValueFactory(new PropertyValueFactory<FactoryAdmin, String>("contact"));
        accountCol.setCellValueFactory(new PropertyValueFactory<FactoryAdmin, String>("account"));
        statusCol.setCellValueFactory(new PropertyValueFactory<FactoryAdmin, String>("factoryStatus"));

    }

    @FXML
    void launchFactory(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            FactoryAdmin selectedFactory = table.getSelectionModel().getSelectedItem();
            selectedFactory.setFactoryStatus("正常");
            initialize(null,null);
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个工厂");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个工厂被选中要启动");
            nullWarning.show();
        }
    }

    @FXML
    void shutFactory(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            FactoryAdmin selectedFactory = table.getSelectionModel().getSelectedItem();
            selectedFactory.setFactoryStatus("关停");
            initialize(null,null);
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个工厂");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个工厂被选中要关停");
            nullWarning.show();
        }
    }
}
