package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

public class DialogWindow {
    private Group dialogGroup;
    private GridPane pane;
    private TextField wordToEncrypt = new TextField();
    private TextField key = new TextField();
    private Button encrypt = new Button("Encrypt");
    private Text ecnrypted;
    private Label wordLabel;
    private Label keyLabel;
    private InfoDialog InfoWindow = new InfoDialog();
    private InfoAlg InfoAlgWindow = new InfoAlg();
    private HBox wordInputWrapper = new HBox();
    private HBox keyInputWrapper = new HBox();

    public DialogWindow() throws FileNotFoundException {
        Font fontEncrypted = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16);
        dialogGroup = new Group();
        pane = new GridPane();


        wordToEncrypt.setPrefWidth(500.0);
        key.setPrefWidth(500.0);
        ecnrypted = new Text(0, 0,"encrypted: ");
        ecnrypted.setWrappingWidth(750);
        ecnrypted.setFont(fontEncrypted);

        wordLabel = new Label("enter wiouiouiyuouiyoord: ");
        wordLabel.setFont(fontEncrypted);

        keyLabel = new Label("enter yoiuoyiuoyuioyuioykey: ");
        keyLabel.setFont(fontEncrypted);

        encrypt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(wordToEncrypt.getText() == "" || key.getText() == "" ||
                    Pattern.matches(".*\\p{InCyrillic}.*", key.getText()) ||
                    Pattern.matches(".*\\p{InCyrillic}.*", wordToEncrypt.getText())){
                    ecnrypted.setText("you have entered wrong data");
                    ecnrypted.setFill(Color.RED);
                }else {
                    String EWord = wordToEncrypt.getText();
                    String KWord = key.getText();
                    Encrypt encryptinst = new Encrypt();
                    String encrypted = encryptinst.encrypt(EWord, KWord);
                    ecnrypted.setText("encrypted: " + encrypted);
                    ecnrypted.setFill(Color.color(0, 0, 0));
                }

            }
        });

        Button buttonStudent = new Button("Info about student");
        Button buttonAlg = new Button("Info about algorithm");
        Label space = new Label("   ");
        HBox buttonPos = new HBox();
        buttonPos.setLayoutX(25.0);
        buttonPos.setLayoutY(15.0);

        buttonStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Main.newWindow.setTitle("Second Stage");
                Main.newWindow.setScene(InfoWindow.returnDialogScene());
                Main.newWindow.setX(Main.primaryStage.getX() + 200);
                Main.newWindow.setY(Main.primaryStage.getY() + 100);
                Main.newWindow.show();
            }
        });

        buttonAlg.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Main.newWindow.setTitle("Second Stage");
                Main.newWindow.setScene(InfoAlgWindow.returnDialogScene());
                Main.newWindow.setX(Main.primaryStage.getX() + 200);
                Main.newWindow.setY(Main.primaryStage.getY() + 100);
                Main.newWindow.show();
            }
        });

        buttonPos.getChildren().add(buttonStudent);
        buttonPos.getChildren().add(space);
        buttonPos.getChildren().add(buttonAlg);

        wordInputWrapper.getChildren().add(wordLabel);
        wordInputWrapper.getChildren().add(wordToEncrypt);

        keyInputWrapper.getChildren().add(keyLabel);
        keyInputWrapper.getChildren().add(key);

        pane.getColumnConstraints().add(new ColumnConstraints(800));
        pane.getRowConstraints().add(new RowConstraints(75));
        pane.getRowConstraints().add(new RowConstraints(75));
        pane.getRowConstraints().add(new RowConstraints(60));
        pane.getRowConstraints().add(new RowConstraints(60));

        GridPane.setHalignment(wordToEncrypt, HPos.CENTER);
        GridPane.setHalignment(key, HPos.CENTER);
        GridPane.setHalignment(encrypt, HPos.RIGHT);
        GridPane.setHalignment(ecnrypted, HPos.LEFT);

        pane.setPadding(new Insets(100, 150, 100, 150));

        pane.add(wordInputWrapper, 0, 0);
        pane.add(keyInputWrapper, 0, 1);
        pane.add(encrypt, 0, 2);
        pane.add(ecnrypted, 0, 3);

        dialogGroup.getChildren().add(pane);
        dialogGroup.getChildren().add(buttonPos);
    }



    public Scene returnDialogScene(){
        return new Scene(dialogGroup, 1280, 720, Color.BEIGE);
    }

}
