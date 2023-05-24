
import entity.UserConfigManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

/**
 * Main class for the application
 */
public class MainApp extends Application {

    private double xOffSet;
    private double yOffSet;

    /**
     * Main method for the application
     * @param args The arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start the application
     * @param primaryStage The primary stage
     * @throws IOException The exception
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        boolean use_psw = UserConfigManager.getInstance().getUserConfig().isNeedPassword();
        System.out.println(use_psw);
        Parent login = null;
        if(use_psw){
            login = (Parent) FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        }else {
            login = (Parent) FXMLLoader.load(getClass().getResource("/view/StudentMenu.fxml"));
        }

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
