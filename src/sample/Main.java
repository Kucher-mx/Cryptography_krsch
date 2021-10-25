package sample;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends Application {

    static DialogWindow dialogWindow;
    static Stage primaryStage;
    static Stage newWindow = new Stage();


    public static void setSetupStage() throws FileNotFoundException {
        dialogWindow = new DialogWindow();
        Main.primaryStage.setTitle("Vigenere cipher");
        Main.primaryStage.setScene(dialogWindow.returnDialogScene());
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
