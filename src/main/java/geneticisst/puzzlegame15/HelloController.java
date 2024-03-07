package geneticisst.puzzlegame15;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    void statsBtnPressed() {
        System.out.println("Stats");
    }

    @FXML
    void settingsBtnPressed() {
        System.out.println("Settings");
    }

    public void quitBtnPressed() {
        System.exit(0);
    }
}
