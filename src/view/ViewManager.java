package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ViewManager {
    private static double yOffSet;
    private static double xOffSet;

    public static Object newWindow(String fxmlFileName) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(ViewManager.class.getResource(fxmlFileName));
            Parent root =loader.load();
            root.setOnMousePressed(event -> {
                xOffSet = event.getSceneX();
                yOffSet = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - xOffSet);
                stage.setY(event.getScreenY() - yOffSet);
            });
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader.getController();
    }

    public static AnchorPane getPane(String fxmlFileName) {
        AnchorPane pane = null;
        try {
            pane = (AnchorPane) new FXMLLoader().load(ViewManager.class.getResource(fxmlFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pane;
    }
}
