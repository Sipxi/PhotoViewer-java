
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class About {

    @FXML
    private Button exitButton;

    public void onButtonExit(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    
}
