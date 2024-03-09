package geneticisst.puzzlegame15;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {

    public Button menuBtn;

    @FXML
    void menuBtnPressed() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SettingsController.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        Stage stage = (Stage) menuBtn.getScene().getWindow();
        stage.setScene(scene);
    }
}
