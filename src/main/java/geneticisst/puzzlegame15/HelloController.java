package geneticisst.puzzlegame15;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class HelloController {
    public Button playBtn, statsBtn, settingsBtn;
    public VBox mainVBox;
    @FXML
    private Label titleText;

    @FXML
    void playBtnPressed() {
        System.out.println("Play");
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
        System.out.println("Quit");
    }
}