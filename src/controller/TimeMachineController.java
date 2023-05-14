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
    private final String modulesFileFormat = "\\{\"id\":\"[^\"]*\",\"name\":\"[^\"]*\",\"type\":\"[^\"]*\",\"spec\":\"[^\"]*\",\"description\":\"[^\"]*\",\"mark\":\"[^\"]*\",\"credit\":\"[^\"]*\"\\}";



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

        // 创建一个FileChooser对话框
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Please select jason file you want to upload.");

        // 设置文件过滤器，只允许选择JSON文件
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON File (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        // 显示对话框并等待用户选择文件
        File selectedFile = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());

        if (selectedFile != null) {
//            // 读取用户选择的文件并将其转换为JSON对象
//            JSONObject jsonObject = readJsonFromFile(selectedFile);

            // 检查JSON对象是否符合您要求的格式
            boolean isJsonValid = validateJson(selectedFile, fileFormat);

            if (isJsonValid) {
                // 将文件替换本地服务器中的指定文件
                replaceJsonFile(selectedFile, replaceFilePath);

                // 向用户显示一个成功消息
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successfully uploaded");
                alert.setHeaderText(null);
                alert.setContentText("The File has been successfully replaced.");
                alert.showAndWait();
            } else {
                // 向用户显示一个警告消息，告诉用户文件格式不正确
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Wrong Format");
                alert.setHeaderText(null);
                alert.setContentText("Please select a json file satisfy format requirement.");
                alert.showAndWait();
            }
        }
    }

    private void replaceJsonFile(File selectedFile, String filePath) {


        try {
            // 创建输入流读取选定的文件
            FileInputStream fis = new FileInputStream(selectedFile);
            // 创建输出流写入指定目录下的文件
            FileOutputStream fos = new FileOutputStream(filePath);

            // 缓存数组，一次性读取写入
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            // 关闭输入输出流
            fis.close();
            fos.close();

            System.out.println("Json file replaced successfully.");

        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }


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
