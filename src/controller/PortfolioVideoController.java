package controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class PortfolioVideoController {
    @FXML
    private MediaPlayer mediaPlayer;
    @FXML
    private MediaView mediaView;

    @FXML
    private Button playVideoButton;
    @FXML
    private FontAwesomeIconView exitButton;



    @FXML
    private void initialize() {
        String videoFile = "data\\video\\guiwu.mp4";
        Media media = new Media(new File(videoFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        playVideoButton.setOnAction(event -> {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
            }else {
                mediaPlayer.play();
            }
        });
    }

    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }




}
