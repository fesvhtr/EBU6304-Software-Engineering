package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;

import com.jfoenix.controls.JFXListView;
import entity.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import view.ViewManager;



import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;

import javafx.util.Callback;

import javafx.event.EventHandler;

/**
 * The controller for the portfolio page.
 */
public class PortfolioInfoController extends InfoController
{
    @FXML
    private JFXListView<String> list;
    @FXML
    private TableView<Portfolio> table;
    @FXML
    private TableColumn<Portfolio, String> titleCol;
    @FXML
    private TableColumn<Portfolio, String> dateCol;
    @FXML
    private TableColumn<Portfolio, String> sizeCol;

    @FXML
    private JFXButton viewButton;
    @FXML
    private Label title;


    private ObservableList<String> portfolioTypeObservableList = FXCollections.observableArrayList();
    private ObservableList<Portfolio> portfolioObservableList = FXCollections.observableArrayList();

    /**
     * Initialize the portfolio page.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        portfolioTypeObservableList.clear();
        portfolioTypeObservableList.add("Video");
        portfolioTypeObservableList.add("Poster");
        portfolioTypeObservableList.add("Article");
        title.setText("Portfolio");


        list.setCellFactory(new Callback<ListView<String>, ListCell<String>>()
        {
            @Override
            public ListCell<String> call(ListView<String> listView)
            {
                ListCell<String> cell = new ListCell<String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        setText(item);
                    }
                };

                cell.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        if (!cell.isEmpty())
                        {
                            String selectedItem = cell.getItem();
                            refreshTable(selectedItem);
                        }
                    }
                });

                return cell;
            }
        });
        list.setItems(portfolioTypeObservableList);

        list.getSelectionModel().selectFirst();
        titleCol.setCellValueFactory(new PropertyValueFactory<Portfolio, String>("title"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Portfolio, String>("uploadDate"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<Portfolio, String>("size"));

        file = "PortfolioEdit.fxml";
    }

    /**
     * Delete the selected portfolio.
     * @param event The mouse event.
     */
    @FXML
    void delHandled(ActionEvent event)
    {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            Portfolio selectedPortfolio = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION,"It will delete your activity " + selectedPortfolio.getTitle());
            delWarning.setHeaderText("Are you sure?");
            delWarning.setTitle("Warning");
            delWarning.showAndWait().ifPresent(response ->
            {
                if (response == ButtonType.OK)
                {
                    table.getItems().remove(selectedPortfolio);
                    PortfolioManager.getInstance().removeItem(selectedPortfolio);
                    File fileToDelete = new File(selectedPortfolio.getStoreFilePath());
                    fileToDelete.delete();
                    initialize(null, null);
                }
            });
        }
        else
        {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please select item from the table.");
            nullWarning.setTitle("ATTENTION: No item");
            nullWarning.setHeaderText("No item has benn selected.");
            nullWarning.show();
        }
    }

    /**
     * Edit the selected portfolio.
     * @param event The mouse event.
     */
    public void abstractEditHandled(ActionEvent event)
    {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            Portfolio selectedPortfolio = table.getSelectionModel().getSelectedItem();
            PortfolioEditController controller = (PortfolioEditController) ViewManager.newWindow("PortfolioEdit.fxml");
            controller.setInPortfolio(selectedPortfolio);
            controller.setParentController(this);
        }
        else
        {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please select item from the table.");
            nullWarning.setTitle("ATTENTION: No item");
            nullWarning.setHeaderText("No item has benn selected.");
            nullWarning.show();
        }
    }

    /**
     * Refresh the table.
     * @param selectedType The selected type.
     */
    public void refreshTable(String selectedType) {
        portfolioObservableList.clear();
        List<Portfolio> portfolios = PortfolioManager.getInstance().getList();
        for (Portfolio a : portfolios){
            if(a.getType().toString().equals(selectedType)){
                portfolioObservableList.add(a);
            }
        }
        table.setItems(portfolioObservableList);
    }

    /**
     * View the selected portfolio.
     * @param event The mouse event.
     * @throws IOException The exception about the input and output.
     */
    @FXML
    void viewHandled(ActionEvent event) throws IOException
    {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            Portfolio selectedPortfolio = table.getSelectionModel().getSelectedItem();
            PortfolioViewController controller = null;
            switch (selectedPortfolio.getType().toString())
            {
                case "Video":
                    controller = (PortfolioViewController) ViewManager.newWindow("PortfoliosVideo.fxml");
                    controller.setMediaView(selectedPortfolio);
                    controller.setParentController(this);

                break;
                case "Poster":
                    controller = (PortfolioViewController) ViewManager.newWindow("PortfolioImage.fxml");
                    controller.setImageView(selectedPortfolio);
                    controller.setParentController(this);

                break;
                case "Article":
                {
                    // create file chooser
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Save File");

                    // set initial directory and suggested file name
                    fileChooser.setInitialFileName(selectedPortfolio.getTitle() + ".pdf");
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

                    // show save file dialog
                    File outputFile = fileChooser.showSaveDialog(viewButton.getScene().getWindow());
                    if (outputFile != null)
                    {
                        try
                        {
                            // copy file from server to local disk
                            Files.copy(new File(selectedPortfolio.getStoreFilePath()).toPath(),
                                    outputFile.toPath());
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }}
                break;
            }
        }
        else
        {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "Please select item from the table.");
            nullWarning.setTitle("ATTENTION: No item");
            nullWarning.setHeaderText("No item has benn selected.");
            nullWarning.show();
        }
    }

}





