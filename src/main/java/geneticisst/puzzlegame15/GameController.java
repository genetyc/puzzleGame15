package geneticisst.puzzlegame15;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GameController {

    public Label titleText;
    public Button menuBtn;
    public GridPane field;
    public Pane pane;
    private List<Node> buttonList;

    @FXML
    private void initialize() {
        buttonList = field.getChildren().stream().filter(it -> it instanceof Button).toList();
        System.out.println(buttonList.size());
    }

    @FXML
    private void onMouseMoved(MouseEvent event) {
        double mouseX = event.getSceneX();
        double mouseY = event.getSceneY();
        //bot-right = 530, 630
        //top-right = 530, 370
        //top-left = 270, 370
        //bot-left = 270, 630
        //square = 65

        Point2D mousePoint = pane.sceneToLocal(mouseX, mouseY);

        if (field.getBoundsInParent().contains(mousePoint)) {
            System.out.println("Курсор в пределах GridPane");
            System.out.printf("%f -> %f", mouseX, mouseY);
        } else {
            System.out.println("Курсор вне GridPane");
        }
    }

    public void menuBtnPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        Stage stage = (Stage) menuBtn.getScene().getWindow();
        stage.setScene(scene);
    }
}
