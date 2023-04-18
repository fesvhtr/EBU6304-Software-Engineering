package controller;

import entity.Order;
import entity.OrderManager;
import entity.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MyOrderController implements Initializable {

    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order, String> idCol;

    @FXML
    private TableColumn<Order, String> nameCol;

    @FXML
    private TableColumn<Order, String> amountCol;

    @FXML
    private TableColumn<Order, String> deliveryDateCol;

    @FXML
    private TableColumn<Order, String> dueDateCol;

    @FXML
    private TableColumn<Order, String> receiverCol;

    @FXML
    private TableColumn<Order, String> contactCol;

    @FXML
    private TableColumn<Order, String> addressCol;

    @FXML
    private TableColumn<Order, String> statusCol;

    @FXML
    void delHandled(ActionEvent event) {

    }

    @FXML
    void editHandled(ActionEvent event) {

    }

    @FXML
    void newHandled(ActionEvent event) {

    }

    private ObservableList<Order> orderObservableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderObservableList.clear();
        List<Order> orders = OrderManager.getInstance().getOrderList();
        for (Order o : orders) {
            if (o.getReceiver().equals(UserManager.getInstance().getCurrentUser().getName()))
                orderObservableList.add(o);
        }
        table.setItems(orderObservableList);
        idCol.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("product"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Order, String>("amount"));
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<Order, String>("deliveryDate"));
        dueDateCol.setCellValueFactory(new PropertyValueFactory<Order, String>("dueDate"));
        receiverCol.setCellValueFactory(new PropertyValueFactory<Order, String>("receiver"));
        contactCol.setCellValueFactory(new PropertyValueFactory<Order, String>("contact"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Order, String>("address"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
    }
}
