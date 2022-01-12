import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));

        Scene scene = new Scene(root, 1280, 800);

        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setTitle("Image Viewer");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show(); 
        }


}