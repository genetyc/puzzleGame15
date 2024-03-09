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
    private Button emptyCell;
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
                    button.setOnAction(node -> swap(button));
                    buttonList.add(button);
                }
                else {
                    emptyCell = new Button();
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

    private void onBtnClick(Button button) {

        int[] coord = checkTiles(button);
        if (coord[0] != -1) {
            System.out.printf("Old row -> %d, old column -> %d, new row -> %d, new column -> %d%n", coord[0], coord[1], coord[2], coord[3]);
        } else {
            System.out.println("CLREAR");
        }

    }

    private void swap(Button button) {
        int btnRow = GridPane.getRowIndex(button);
        int btnColumn = GridPane.getColumnIndex(button);
        int emptyRow = emptyCellCoord.getFirst();
        int emptyColumn = emptyCellCoord.getSecond();
        int pos = btnRow * fieldSize + btnColumn;
        int emptyCellPos = emptyRow * fieldSize + emptyColumn + 1;
        if (field.getChildren().get(pos + 1) instanceof Button) {
            System.out.printf("Empty: row -> %d, column -> %d%n", emptyRow, emptyColumn);
            System.out.printf("Prev: row -> %d, column -> %d%n", btnRow, btnColumn);
            button.setVisible(false);
            emptyCell.setVisible(true);
            emptyCell.setText(button.getText());
            emptyCellCoord = new Pair<>(btnRow, btnColumn);
            emptyCell.setOnAction(node -> swap(emptyCell));
            emptyCell = button;
            System.out.printf("Empty: row -> %d, column -> %d%n", emptyCellCoord.getFirst(), emptyCellCoord.getSecond());

        }
    }

    private void newEmptyCell() {

    }

    private Pair<Integer, Integer> checkNearbyTiles(Button button) {
        int btnRow = GridPane.getRowIndex(button);
        int btnColumn = GridPane.getColumnIndex(button);
        for (int row = btnRow - 1; row <= btnRow + 1; row+=2) {
            if (0 < row && row < fieldSize) {
                if (field.getChildren().get(row * 4 + btnColumn) instanceof javafx.scene.Group) {
                    return new Pair<>(row, btnColumn);
                }
            }
        }
        for (int column = btnColumn - 1; column <= btnColumn + 1; column+=2) {
            if (0 < column && column < fieldSize) {
                if (field.getChildren().get(btnRow * 4 + column) instanceof javafx.scene.Group) {
                    return new Pair<>(btnRow, column);
                }
            }
        }
        return new Pair<>(-1, -1);
    }

    private int[] checkTiles(Button button) {
        int btnRow = GridPane.getRowIndex(button);
        int btnColumn = GridPane.getColumnIndex(button);
        System.out.printf("Row -> %d, column -> %d%n", btnRow, btnColumn);
        for (int row = btnRow - 1; row <= btnRow + 1; row+=2) {
            if (0 < row && row < fieldSize) {
                if (field.getChildren().get(row * 4 + btnColumn) instanceof javafx.scene.Group) {
                    return new int[]{btnRow, btnColumn, row, btnColumn};
                }
            }
        }
        for (int column = btnColumn - 1; column <= btnColumn + 1; column+=2) {
            if (0 < column && column < fieldSize) {
                if (field.getChildren().get(btnRow * 4 + column) instanceof javafx.scene.Group) {
                    return new int[]{btnRow, btnColumn, btnRow, column};
                }
            }
        }
        return new int[]{-1, -1, -1, -1};
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
