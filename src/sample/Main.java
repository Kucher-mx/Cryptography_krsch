package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Main extends Application {

    static DialogWindow dialogWindow;
    static Stage primaryStage;
    static Stage newWindow = new Stage();
    static Color BACKGROUND_COLOR = Color.BEIGE;

    public static void setSetupStage() throws FileNotFoundException {
        dialogWindow = new DialogWindow();
        Main.primaryStage.setTitle("Vigenere cipher");
        dialogWindow.setDialogScene();
        Main.primaryStage.setWidth( 1000 );
        Main.primaryStage.setHeight( 600 );
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        Main.primaryStage.show();
        Animated animated = new Animated();
        animated.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
        launch(args);
    }
}
