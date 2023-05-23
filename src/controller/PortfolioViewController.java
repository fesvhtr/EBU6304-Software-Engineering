package controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Portfolio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The controller for the portfolio view page.
 */
public class PortfolioViewController {
    @FXML
    private MediaPlayer mediaPlayer;
    @FXML
    private MediaView mediaView;
    @FXML
    private ImageView imageView;

    @FXML
    private Button playVideoButton;
    @FXML
    private WebView webView;
    @FXML
    private FontAwesomeIconView exitButton;

    private Portfolio inPortfolio;

    private PortfolioController protfolioController;

    /**
     * Set the parent controller.
     * @param controller the parent controller
     */
    public void setParentController(PortfolioController controller) {
        protfolioController = controller;
    }

    /**
     * Set the video to be displayed.
     * @param portfolio the video to be displayed
     */
    public void setMediaView(Portfolio portfolio) {
        this.inPortfolio = portfolio;
        Media media = new Media(new File(inPortfolio.getStoreFilePath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    /**
     * Set the picture to be displayed.
     * @param portfolio the picture to be displayed
     * @throws UnsupportedEncodingException if the encoding is not supported
     */
    public void setImageView(Portfolio portfolio) throws UnsupportedEncodingException {
        this.inPortfolio = portfolio;
        File file = new File(inPortfolio.getStoreFilePath());
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    /**
     * Close the video.
     * @param event the mouse event
     */
    @FXML
    void closeVideo(MouseEvent event) {
        mediaPlayer.stop();
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();

    }

    /**
     * Close the page.
     * @param event the mouse event
     */
    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();

    }

    /**
     * Play or pause the video.
     * @param event the mouse event
     */
    @FXML
    public void setMediaStatus(ActionEvent event) {

        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }else {
            mediaPlayer.play();
        }

    }

}
