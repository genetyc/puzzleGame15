package geneticisst.puzzlegame15;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class HelloController {
    public Button playBtn, statsBtn, settingsBtn;
    public VBox mainVBox;
    @FXML
    private Label titleText;

    @FXML
    void playBtnPressed() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameController.class.getResource("play-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        Stage game = (Stage) playBtn.getScene().getWindow();
        game.setScene(scene);
    }

    @FXML
    void statsBtnPressed() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SettingsController.class.getResource("stats-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        Stage stage = (Stage) statsBtn.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void settingsBtnPressed() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SettingsController.class.getResource("settings-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        Stage stage = (Stage) settingsBtn.getScene().getWindow();
        stage.setScene(scene);
    }

    public void quitBtnPressed() {
        System.exit(0);
    }
}
