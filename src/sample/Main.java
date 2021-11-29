package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import javafx.scene.paint.Color;

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
