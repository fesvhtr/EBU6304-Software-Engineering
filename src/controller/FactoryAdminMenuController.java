package controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-21 11:13
 */
public class FactoryAdminMenuController implements Initializable {

    @FXML
    private BorderPane rootLayout;
    @FXML
    private JFXButton myDeviceButton;

    @FXML
    private Label helloText;

    @FXML
    private JFXButton receiveOrderButton;

    @FXML
    private JFXButton arrangeProductionButton;

    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void showMyDevice(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("MyDevice.fxml"));
    }

    @FXML
    void showOrderArrangement(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("ReceiveOrder.fxml"));
    }

    @FXML
    void showOrders(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("ArrangeOrderList.fxml"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        helloText.setText("你好，云厂主" + UserManager.getInstance().getCurrentUser().getName());
    }
}
