package geneticisst.puzzlegame15;

import com.almasb.fxgl.core.collection.grid.Grid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kotlin.Pair;

import java.io.IOException;
import java.util.*;
import java.util.random.*;

public class GameController {
    private Map<Integer, String> assignments = Map.ofEntries(
            Map.entry(1, "00"), Map.entry(2, "01"), Map.entry(3, "02"), Map.entry(4, "03"),
            Map.entry(5, "10"), Map.entry(6, "11"), Map.entry(7, "12"), Map.entry(8, "13"),
            Map.entry(9, "20"), Map.entry(10, "21"), Map.entry(11, "22"), Map.entry(12, "23"),
            Map.entry(13, "30"), Map.entry(14, "31"), Map.entry(15, "32")
    );
    public int fieldSize = 4;

    public Label titleText;
    public Button menuBtn;
    public GridPane field;
    public Pane pane;
    private List<Node> buttonList = new ArrayList<>(15);
    private Pair<Integer, Integer> emptyCellCoord = new Pair<>(fieldSize - 1, fieldSize - 1);
    private List<Integer> leftNums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

    @FXML
    private void initialize() {
        Collections.shuffle(leftNums);
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                int pos = i * fieldSize + j;
                if (pos < fieldSize * fieldSize - 1) {
                    Button button = new Button(String.valueOf(leftNums.get(pos)));
                    field.add(button, j, i);
                    button.setOnAction(node -> checkNearbyTiles(button));
                    buttonList.add(button);
                }
                else {
                    Button emptyCell = new Button();
                    emptyCell.setVisible(false);
                    field.add(emptyCell, fieldSize - 1, fieldSize - 1);
                }
            }
        }


    }

/*
         <children>
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="0" GridPane.rowIndex="0"/>
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="1" GridPane.rowIndex="0"/>
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="2" GridPane.rowIndex="0"/>
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="3" GridPane.rowIndex="0"/>
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="0" GridPane.rowIndex="1" />
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="1" GridPane.rowIndex="1" />
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="2" GridPane.rowIndex="1" />
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="3" GridPane.rowIndex="1" />
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="0" GridPane.rowIndex="2" />
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="1" GridPane.rowIndex="2" />
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="2" GridPane.rowIndex="2" />
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="3" GridPane.rowIndex="2" />
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="0" GridPane.rowIndex="3" />
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="1" GridPane.rowIndex="3" />
            <Button alignment="CENTER" contentDisplay="CENTER" mnemonicParsing="false" prefHeight="50.0" prefWidth="50.0" text="Button" GridPane.columnIndex="2" GridPane.rowIndex="3" />
         </children>
         */

    private void swap(Button button) {
        int btnRow = GridPane.getRowIndex(button);
        int btnColumn = GridPane.getColumnIndex(button);
        Button emptyCell = new Button();
        emptyCell.setVisible(false);
        field.getChildren().remove(button);
        field.add(emptyCell, btnColumn, btnRow);
        field.add(button, emptyCellCoord.getSecond(), emptyCellCoord.getFirst());
        emptyCellCoord = new Pair<>(btnRow, btnColumn);
    }

    private void checkNearbyTiles(Button button) {
        int btnRow = GridPane.getRowIndex(button);
        int btnColumn = GridPane.getColumnIndex(button);
        int eRow = emptyCellCoord.getFirst();
        int eColumn = emptyCellCoord.getSecond();
        if (btnRow == eRow || btnColumn == eColumn) {
            if (Math.abs(btnRow - eRow) == 1 || Math.abs(btnColumn - eColumn) == 1) {
                swap(button);
            }
        }
    }

    public void menuBtnPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        Stage stage = (Stage) menuBtn.getScene().getWindow();
        stage.setScene(scene);
    }
}
