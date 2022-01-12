
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.Color;

public class Controller implements Initializable {

    BufferedImage img;
    private BufferedImage originalImage;
    private FileChooser fc;

    @FXML
    private ImageView imgFrame;
    @FXML
    private TextArea textLogger;
    @FXML
    private MenuItem saveFile;
    @FXML
    private CheckBox imageChose;
    @FXML
    private MenuItem negativeFilterItem;
    private File file;

    public Controller() {
        this.file = null;
        this.img = null;
        this.originalImage = null;
        this.fc = new FileChooser();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Auto-generated method stub
        textLogger.setEditable(false);
        saveFile.setDisable(true);
        negativeFilterItem.setDisable(true);

    }

    public void clearImg() {
        this.img = null;
        this.originalImage = null;
    }

    public void addLog(final String text) {
        textLogger.appendText("\n" + text);
    }

    public void makePopUp(String fxmlName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlName));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();

    }

    public void resetToOriginal() throws IOException {
        this.img = ImageUtils.readImageFromFile(this.file);
        this.originalImage = ImageUtils.readImageFromFile(this.file);
    }

    public void MenuItemOpenAction(ActionEvent event) {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.png");
        fc.getExtensionFilters().add(extFilter);
        try {
            // Open the pop up
            File file = this.fc.showOpenDialog(null);
            this.file = file;

            // Read the file, buffer it
            this.img = ImageUtils.readImageFromFile(this.file);
            this.originalImage = ImageUtils.readImageFromFile(this.file);

            // Convert to FXImage and display
            imgFrame.setImage(SwingFXUtils.toFXImage(this.img, null));

            saveFile.setDisable(false);
            negativeFilterItem.setDisable(false);

            this.addLog("Successfully loaded Image");
        } catch (Exception e) {
            this.addLog("Error while loading image:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void generateImage() {
        // Image file dimensions
        int width = 640, height = 320;
  
        // Create buffered image object
        BufferedImage img = null;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
  
        // create values pixel by pixel
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                img.setRGB(x, y, new Color(255, 255, 255).getRGB());
            }
        this.img = img;
        this.originalImage = img;
        imgFrame.setImage(SwingFXUtils.toFXImage(img, null));
        
    }
}

    public void MenuItemSaveAction(ActionEvent event) throws IOException {

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.png");
        fc.getExtensionFilters().add(extFilter);

        try {
            // Show the pop up
            File file = this.fc.showSaveDialog(null);
            // Save the file

            ImageUtils.writeImageToPNG(file, this.img);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void MenuItemCloseAction(ActionEvent event) {
        try {
            this.clearImg();
            imgFrame.setImage(null);
            saveFile.setDisable(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ExitItemAction(ActionEvent event) {
        Platform.exit();
    }

    public void MatchCheckBoxAction() {

        if (this.img == null) {
            return;
        }
        if (imageChose.isSelected()) {
            imgFrame.setImage(SwingFXUtils.toFXImage(this.originalImage, null));
        } else {
            imgFrame.setImage(SwingFXUtils.toFXImage(this.img, null));
        }
    }

    public void NegativeItemAction(ActionEvent event) throws IOException {
        NegativeFilter filter = new NegativeFilter(this.img);
        filter.useFilter();
        imgFrame.setImage(SwingFXUtils.toFXImage(this.img, null));
    }

    public void AboutItemAction(ActionEvent event) throws IOException {

        this.makePopUp("About.fxml");
    }

    public void ResetButtonAction(ActionEvent event) throws IOException {
        this.resetToOriginal();
        this.originalImage = ImageUtils.readImageFromFile(this.file);
    }

    public void updateFractal(){
        /*
        Fractal fractal = new Fractal();
        img = fractal.getFractal();
        imgFrame.setImage(SwingFXUtils.toFXImage(img, null));
        */
    }
}
