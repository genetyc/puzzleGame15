module geneticisst.puzzlegame15 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens geneticisst.puzzlegame15 to javafx.fxml;
    exports geneticisst.puzzlegame15;
}