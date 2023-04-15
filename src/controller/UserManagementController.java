package controller;

import entity.*;
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
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-22 4:47
 */
public class UserManagementController implements Initializable {

    @FXML
    private TableView<AbstractUser> table;

    @FXML
    private TableColumn<AbstractUser, String> accountCol;

    @FXML
    private TableColumn<AbstractUser, String> nameCol;

    @FXML
    private TableColumn<AbstractUser, String> contactCol;

    @FXML
    private TableColumn<AbstractUser, String> roleCol;


    @FXML
    void delUser(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            AbstractUser selectedUser = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION,"确定删除" + selectedUser.getName() + "吗？");
            delWarning.setHeaderText("删除确认");
            delWarning.setTitle("稍等下。。");
            delWarning.showAndWait().ifPresent(response ->{
                if (response == ButtonType.OK) {
                    table.getItems().remove(selectedUser);
                    UserManager.getInstance().remove(selectedUser);
                }
            });
        } else {
            Alert nullwarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个人");
            nullwarning.setTitle("提示：未选中任何项哦");
            nullwarning.setHeaderText("没有一个人被选中要删除");
            nullwarning.show();
        }
    }

    @FXML
    void editUser(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            AbstractUser selectedUser = table.getSelectionModel().getSelectedItem();
            UserEditController controller = (UserEditController) ViewManager.newWindow("UserEdit.fxml");
            controller.setUser(selectedUser);
            controller.setParentController(this);
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个人");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个人被选中要编辑");
            nullWarning.show();
        }
    }

    @FXML
    void newUser(ActionEvent event) {
        RegisterController controller = (RegisterController) ViewManager.newWindow("register.fxml");
        controller.setParentController(this);
    }

    private ObservableList<AbstractUser> userObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userObservableList.clear();
        List<FactoryAdmin> factoryAdmins = UserManager.getInstance().getFactoryAdmins();
        List<Retailer> retailers = UserManager.getInstance().getRetailers();
        List<SuperAdmin> superAdmins = UserManager.getInstance().getSuperAdmins();
        for (FactoryAdmin fa : factoryAdmins) {
            userObservableList.add(fa);
        }
        for (Retailer r : retailers) {
            userObservableList.add(r);
        }
        for (SuperAdmin sa : superAdmins) {
            userObservableList.add(sa);
        }
        table.setItems(userObservableList);
        accountCol.setCellValueFactory(new PropertyValueFactory<AbstractUser, String>("account"));
        nameCol.setCellValueFactory(new PropertyValueFactory<AbstractUser, String>("name"));
        contactCol.setCellValueFactory(new PropertyValueFactory<AbstractUser, String>("contact"));
        roleCol.setCellValueFactory(new PropertyValueFactory<AbstractUser, String>("role"));
    }

}
