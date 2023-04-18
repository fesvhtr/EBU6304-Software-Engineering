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



public class RetailerMenuController implements Initializable {

    @FXML
    private BorderPane rootLayout;
    @FXML
    private FontAwesomeIconView exitButton;
    @FXML
    private Label welcomeText;

    @FXML
    private JFXButton nyOrderButton;

    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void showMyOrder(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("MyOrder.fxml"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeText.setText("你好，经销商" + UserManager.getInstance().getCurrentUser().getName());
    }
}

