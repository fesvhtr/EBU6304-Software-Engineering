
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApp extends Application {

    private double xOffSet;
    private double yOffSet;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent login = (Parent) FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene loginScene = new Scene(login);
        primaryStage.getIcons().add(new Image("view/image/icons8_google_cloud_print_48px.png"));
        login.setOnMousePressed(event -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        login.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffSet);
            primaryStage.setY(event.getScreenY() - yOffSet);
        });
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }


}
