package geneticisst.puzzlegame15;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Parent mainWindow() {
        Pane pane = new Pane();

        pane.setPrefSize(800, 800);
        return pane;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(mainWindow());
        stage.setTitle("Fifteen");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}