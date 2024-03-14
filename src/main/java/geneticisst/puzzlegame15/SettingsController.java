package geneticisst.puzzlegame15;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    public static int fieldSize = 4;
    public HBox fieldSizeGroup;
    public ToggleGroup fieldSizeRadio = new ToggleGroup();
    public Button menuBtn;

    @FXML
    public void initialize() {
        for (Node node : fieldSizeGroup.getChildren()) {
            if (node instanceof RadioButton) {
                ((RadioButton) node).setToggleGroup(fieldSizeRadio);
            }
        }
        fieldSizeRadio.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            fieldSize = Integer.parseInt(((RadioButton)newVal).getText().substring(0,1));
            System.out.println(fieldSize);
        });

        //fieldSizeRadio.selectedToggleProperty().addListener((observable, oldVal, newVal) -> System.out.println(newVal + " was selected"));
    }

    @FXML
    void menuBtnPressed() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SettingsController.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        Stage stage = (Stage) menuBtn.getScene().getWindow();
        stage.setScene(scene);
    }
}
