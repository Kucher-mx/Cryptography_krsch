package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import javafx.scene.paint.Color;

public class Main extends Application {

    static DialogWindow dialogWindow;
    static Stage primaryStage;
    static Stage newWindow = new Stage();
    static Color BACKGROUND_COLOR = Color.YELLOW;
    static Scene mainScene = new Scene(new Group(), 1280, 720);

    public static void setSetupStage() throws FileNotFoundException {
        dialogWindow = new DialogWindow();
        Main.primaryStage.setTitle("Vigenere cipher");
//        Main.primaryStage.setScene(dialogWindow.returnDialogScene());
//        Main.primaryStage.setScene(Main.mainScene);
        dialogWindow.setDialogScene();
        Main.primaryStage.setWidth( 1000 );
        Main.primaryStage.setHeight( 600 );
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        Main.setSetupStage();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
