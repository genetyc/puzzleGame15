package geneticisst.puzzlegame15;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kotlin.Pair;

import java.io.IOException;
import java.util.*;

public class GameController {
    public int fieldSize = 4;

    public Label titleText;
    public Label movesCounter;
    public int moves;
    public Button menuBtn;
    public GridPane field;
    public Pane pane;
    private List<Button> buttonList = new ArrayList<>(fieldSize * fieldSize - 1);
    private Pair<Integer, Integer> emptyCellCoord = new Pair<>(fieldSize - 1, fieldSize - 1);
    private List<Integer> leftNums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    public Label tilesCounter;
    private int tilesCorrect = 0;

    @FXML
    private void initialize() {
        Collections.shuffle(leftNums);
        moves = 0;
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                int pos = i * fieldSize + j;
                if (pos < fieldSize * fieldSize - 1) {
                    Button button = new Button(String.valueOf(leftNums.get(pos)));
                    field.add(button, j, i);
                    button.setOnAction(node -> checkNearbyTiles(button));
                    buttonList.add(button);
                    if (i * fieldSize + j + 1 == Integer.parseInt(button.getText())) {
                        tilesCorrect++;
                    }
                }
                else {
                    Button emptyCell = new Button();
                    emptyCell.setVisible(false);
                    field.add(emptyCell, fieldSize - 1, fieldSize - 1);
                }
            }
        }
        tilesCounter.setText("Tiles home: " + tilesCorrect);


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
        movesCounter.setText("Moves: " + ++moves);
    }

    private void checkNearbyTiles(Button button) {
        int btnRow = GridPane.getRowIndex(button);
        int btnColumn = GridPane.getColumnIndex(button);
        int eRow = emptyCellCoord.getFirst();
        int eColumn = emptyCellCoord.getSecond();
        if (btnRow == eRow || btnColumn == eColumn) {
            int text = Integer.parseInt(button.getText());
            if (Math.abs(btnRow - eRow) == 1 || Math.abs(btnColumn - eColumn) == 1) {
                swap(button);
                if (btnRow * fieldSize + btnColumn + 1 == text) {
                    tilesCorrect--;
                    tilesCounter.setText("Tiles home: " + tilesCorrect);
                } if (eRow * fieldSize + eColumn + 1 == text) {
                    tilesCorrect++;
                    tilesCounter.setText("Tiles home: " + tilesCorrect);
                    if (tilesCorrect == fieldSize * fieldSize - 1) {
                        gameOver();
                    }
                }
            }
        }
    }

    private void gameOver() {
        System.out.println("Game over");
    }

    public void menuBtnPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        Stage stage = (Stage) menuBtn.getScene().getWindow();
        stage.setScene(scene);
    }
}
