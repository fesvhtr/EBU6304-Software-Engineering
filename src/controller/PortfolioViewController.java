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

//    @FXML
//    private Label storePathLabel;
    private Portfolio inPortfolio;

    private PortfolioController protfolioController;


    public void setParentController(PortfolioController controller) {
        protfolioController = controller;
    }
    @FXML
    private void initialize() {
//        playVideoButton.setOnAction(event -> {
//            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
//                mediaPlayer.pause();
//            }else {
//                mediaPlayer.play();
//            }
//        });
    }

    public void setMediaView(Portfolio portfolio) {
        this.inPortfolio = portfolio;
        Media media = new Media(new File(inPortfolio.getStoreFilePath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    public void setImageView(Portfolio portfolio) throws UnsupportedEncodingException {
        this.inPortfolio = portfolio;
        File file = new File(inPortfolio.getStoreFilePath());
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }
//
//    public void setWebView(Portfolio portfolio) {
//        // create file chooser
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Save File");
//
//        // set initial directory and suggested file name
//        fileChooser.setInitialFileName(portfolio.getTitle() + ".pdf");
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//
//        // show save file dialog
//        File outputFile = fileChooser.showSaveDialog(downloadButton.getScene().getWindow());
//        if (outputFile != null) {
//            try {
//                // copy file from server to local disk
//                Files.copy(new File(portfolio.getStoreFilePath()).toPath(),
//                        outputFile.toPath());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @FXML
    void closeVideo(MouseEvent event) {
        mediaPlayer.stop();
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();

    }

    @FXML
    void close(MouseEvent event) {
//        mediaPlayer.stop();
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();

    }

@FXML
    public void setMediaStatus(ActionEvent event) {

        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }else {
            mediaPlayer.play();
        }

    }

}
