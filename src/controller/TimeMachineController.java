package controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import entity.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * The controller for the time machine page.
 */
public class TimeMachineController {
    @FXML
    private ComboBox<String> downloadComboBox;
    @FXML
    private ComboBox<String> uploadComboBox;
    @FXML
    private Button downloadButton;
    @FXML
    private Button uploadButton;
    private final String activityFilePath = "data\\Activities.json";
    private final String rolesFilePath = "data\\Roles.json";
    private final String modulesFilePath= "data\\Modules.json";
    private final String rolesFileFormat = "\\{\"title\":\"[^\"]*\",\"startDate\":\"[^\"]*\",\"endDate\":\"[^\"]*\",\"description\":\"[^\"]*\"\\}";
    private final String activitiesFileFormat = "\\{\"title\":\"[^\"]*\",\"role\":\"[^\"]*\",\"startDate\":\"[^\"]*\",\"endDate\":\"[^\"]*\",\"type\":\"[^\"]*\"\\}";
    private final String modulesFileFormat =  "\\{\"id\":\"[^\"]*\",\"name\":\"[^\"]*\",\"type\":\"[^\"]*\",\"description\":\"[^\"]*\",\"mark\":\\d+(\\.\\d+)?,\"credit\":\\d+(\\.\\d+)?\\}";


    /**
     * Upload a file to the server.
     * @param event the event triggered by clicking the upload button.
     */
    @FXML
    private void handleUploadButton(ActionEvent event) {
        String selectedFileFormat = uploadComboBox.getValue();
        String fileFormat = null;
        String replaceFilePath = null;
        switch (selectedFileFormat){
            case "Modules":
                fileFormat = modulesFileFormat;
                replaceFilePath = modulesFilePath;
                break;
            case "Activities":
                fileFormat = activitiesFileFormat;
                replaceFilePath = activityFilePath;
                break;
            case "Roles Undertaken": fileFormat = rolesFileFormat;
            replaceFilePath = rolesFilePath;
                break;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Please select jason file you want to upload.");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON File (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());

        if (selectedFile != null) {
            boolean isJsonValid = validateJson(selectedFile, fileFormat);
            if (isJsonValid) {
                replaceJsonFile(selectedFile, replaceFilePath);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successfully uploaded");
                alert.setHeaderText(null);
                alert.setContentText("The File has been successfully replaced.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Wrong Format");
                alert.setHeaderText(null);
                alert.setContentText("Please select a json file satisfy format requirement.");
                alert.showAndWait();
            }
        }
    }

    /**
     * Replace the specified file in the local server with the selected file.
     * @param selectedFile the file selected by the user.
     * @param filePath the path of the file to be replaced.
     */
    private void replaceJsonFile(File selectedFile, String filePath) {
        try {
            FileInputStream fis = new FileInputStream(selectedFile);
            FileOutputStream fos = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            fis.close();
            fos.close();
            System.out.println("Json file replaced successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Validate the upload file.
     * @param file the file uploaded by the user.
     * @param fileFormat the format should be satisfied.
     * @return true if the file is valid, false otherwise.
     */
    private boolean validateJson(File file, String fileFormat) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.matches(fileFormat)) {
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }









    @FXML
    private void handleDownloadButton(ActionEvent event) {
        String selectedFile = downloadComboBox.getValue();

        switch (selectedFile){
            case "Modules": downloadJasonFile("Modules",modulesFilePath);
            break;
            case "Activities": downloadJasonFile("Activities", activityFilePath);
            break;
            case "Roles Undertaken": downloadJasonFile("Roles", rolesFilePath);
            break;
            case "All": downloadZipFile();
            break;
        }
        
    }



    @FXML
    private void initialize() {
        // 获取可用的JSON文件列表
        List<String> downloadChoice = setDownloadComBox();
        List<String> uploadChoice = setUploadComBox();
        // 将可用的JSON文件添加到下拉列表中
        downloadComboBox.getItems().addAll(downloadChoice);
        uploadComboBox.getItems().addAll(uploadChoice);
    }

    private List<String> setDownloadComBox() {
        // 获取可用的JSON文件列表
        return Arrays.asList( "Modules", "Activities","Roles Undertaken", "All");
    }

    private List<String> setUploadComBox() {
        // 获取可用的JSON文件列表
        return Arrays.asList( "Modules", "Activities","Roles Undertaken");
    }



    private List<File> getZipFiles() {
        List<File> jsonFiles = new ArrayList<>();
        jsonFiles.add(new File(activityFilePath));
        jsonFiles.add(new File(rolesFilePath));
        jsonFiles.add(new File(modulesFilePath));
        return jsonFiles;
    }

    private void downloadJasonFile(String selectedType, String filePath){
        // create file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");

        // set initial directory and suggested file name
        fileChooser.setInitialFileName(selectedType + ".json");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // show save file dialog
        File outputFile = fileChooser.showSaveDialog(downloadButton.getScene().getWindow());
        if (outputFile != null) {
            try {
                // copy file from server to local disk
                Files.copy(new File(filePath).toPath(),
                        outputFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void downloadZipFile(){
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select Download Folder");
        File selectedDirectory = chooser.showDialog(downloadButton.getScene().getWindow());

        if (selectedDirectory != null) {
            List<File> jsonFiles = getZipFiles();

            if (!jsonFiles.isEmpty()) {
                try {
                    // Create a zip file in the selected directory with the name "studentInfo.zip"
                    String zipFileName = selectedDirectory.getAbsolutePath() + File.separator + "studentInfo.zip";
                    FileOutputStream fos = new FileOutputStream(zipFileName);
                    ZipOutputStream zos = new ZipOutputStream(fos);

                    // Add each json file to the zip file
                    for (File jsonFile : jsonFiles) {
                        FileInputStream fis = new FileInputStream(jsonFile);
                        ZipEntry zipEntry = new ZipEntry(jsonFile.getName());
                        zos.putNextEntry(zipEntry);
                        byte[] bytes = new byte[1024];
                        int length;
                        while ((length = fis.read(bytes)) >= 0) {
                            zos.write(bytes, 0, length);
                        }
                        fis.close();
                    }

                    // Close the zip stream
                    zos.close();

                    // Show a success message to the user
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("JSON files downloaded as ZIP to " + selectedDirectory.getAbsolutePath());
                    alert.showAndWait();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Show an error message to the user if there are no JSON files
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No JSON files found");
                alert.showAndWait();
            }
        }
    }

}
