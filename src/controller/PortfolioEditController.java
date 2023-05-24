package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Portfolio;
import entity.PortfolioManager;
import entity.PortfolioTy;
import entity.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The controller for the portfolio edit page.
 */
public class PortfolioEditController implements Initializable {

    @FXML
    private FontAwesomeIconView exitButton;
    @FXML
    private JFXButton importButton;
    @FXML
    private JFXTextField uploadDateField;

    @FXML
    private JFXTextField sizeField;

    @FXML
    private JFXTextField titleField;

    @FXML
    private JFXComboBox<Type> typeComboBox;
    @FXML
    private Label storePathLabel;

    private String filePath = "data/portfolio";


    private Portfolio inPortfolio;
    private PortfolioController protfolioController;


    /**
     * Handle the event when the user click the exit button.
     *
     * @param event the mouse event
     */
    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Handle the event when the user click the save button.
     *
     * @param event the mouse event
     */
    @FXML
    void saveHandled(ActionEvent event) {

        String title = titleField.getText();
        String size = sizeField.getText();
        String uploadDate = uploadDateField.getText();
        String storeFilePath = storePathLabel.getText();


        if (title.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("Your title can't be null");
            alert.show();
            return;
        } else if (typeComboBox.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("No type is selected");
            alert.show();
            return;
        }

        String type = typeComboBox.getSelectionModel().getSelectedItem().toString();
        if (inPortfolio != null) {
            PortfolioManager.getInstance().removeItem(inPortfolio);
        }
        PortfolioManager.getInstance().addItem(new Portfolio(new Type(type), title, uploadDate, size, storeFilePath));
        protfolioController.initialize(null, null);
        Alert info = new Alert(Alert.AlertType.INFORMATION, "New Portfolio saved");
        info.showAndWait();
        protfolioController.initialize(null, null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Set the parent controller.
     *
     * @param controller the parent controller
     */
    public void setParentController(PortfolioController controller) {
        protfolioController = controller;
    }

    /**
     * Set the portfolio to be edited.
     *
     * @param portfolio the portfolio to be edited
     */
    public void setInPortfolio(Portfolio portfolio) {
        inPortfolio = portfolio;
        titleField.setText(portfolio.getTitle());
        uploadDateField.setText(portfolio.getUploadDate());
        sizeField.setText(portfolio.getSize());
        typeComboBox.getSelectionModel().select(portfolio.getType());
    }

    /**
     * Initialize the portfolio edit page.
     *
     * @param location  the URL location
     * @param resources the ResourceBundle
     */
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
        typeObservableList.add(new Type("Video"));
        typeObservableList.add(new Type("Poster"));
        typeObservableList.add(new Type("Article"));
        typeComboBox.setItems(typeObservableList);
    }

    /**
     * Handle the event when the user click the import button.
     *
     * @param event the mouse event
     * @throws IOException the IOException
     */
    @FXML
    private void importHandled(ActionEvent event) throws IOException {

        String title = titleField.getText();
        if (title.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("Your title can't be null");
            alert.show();
            return;
        } else if (typeComboBox.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check again");
            alert.setHeaderText("No type is selected");
            alert.show();
            return;
        }
        String selectedFileFormat = String.valueOf(typeComboBox.getValue());
        String fileFormat = null;
        String importFilePath = filePath;
        String fileFormatDescription = null;

        switch (selectedFileFormat) {
            case "Video":
                fileFormat = "*.mp4";
                fileFormatDescription = "MP4 File (*.mp4)";

                break;
            case "Poster":
                fileFormat = "*.png";
                fileFormatDescription = "PNG File (*.png)";

                break;
            case "Article":
                fileFormat = "*.pdf";
                fileFormatDescription = "PDF File (*.pdf)";
                break;
        }

        // 创建一个FileChooser对话框
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Please select file you want to upload.");

        // 设置文件过滤器，只允许选择JSON文件
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(fileFormatDescription, fileFormat);
        fileChooser.getExtensionFilters().add(extFilter);

        // 显示对话框并等待用户选择文件
        File selectedFile = fileChooser.showOpenDialog(importButton.getScene().getWindow());

        if (selectedFile != null) {
            String storeFilePath = importFile(selectedFile, importFilePath);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully uploaded");
            alert.setHeaderText(null);
            alert.setContentText("The File has been successfully imported.");
            alert.showAndWait();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-hh:mm");
            LocalDateTime now = LocalDateTime.now();
            String formattedDate = now.format(formatter);
            uploadDateField.setText(formattedDate);
            long fileSize = selectedFile.length();
            double fileSizeInMB = (double) fileSize / (1024 * 1024);
            String size = String.format("%.2f", fileSizeInMB) + " MB";
            sizeField.setText(size);
            storePathLabel.setText(storeFilePath);
        }
    }

    /**
     * Import the file to the local server.
     *
     * @param sourceFile     the source file
     * @param destFolderPath the destination folder path
     * @return the destination file path
     * @throws IOException the IOException
     */
    public static String importFile(File sourceFile, String destFolderPath) throws IOException {
        String destFilePath = destFolderPath + File.separator + sourceFile.getName();
        File destFile = new File(destFilePath);
        Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return destFilePath;
    }


}
