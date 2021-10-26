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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private HBox ButtonsWrapper = new HBox();
    private Button save = new Button("Save");
    private Encrypt encryptinst = new Encrypt();

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
                    AudioClip error = new AudioClip(new File("src/source/error.mp3").toURI().toString());
                    error.play();
                    ecnrypted.setText("you have entered wrong data");
                    ecnrypted.setFill(Color.RED);
                }else {
                    String EWord = wordToEncrypt.getText();
                    String KWord = key.getText();
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

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Open file to encrypt");
                    File file = fileChooser.showOpenDialog(Main.primaryStage);
                    String fileName = file.getAbsolutePath();
                    String fileBody = Files.readString(Path.of(fileName));

                    String KWord = key.getText();
                    String encryptedFileBody = encryptinst.encrypt(fileBody, KWord);

                    if(key.getText() == "" || Pattern.matches(".*\\p{InCyrillic}.*", key.getText())){
                        ecnrypted.setText("Enter a valid key");
                        ecnrypted.setFill(Color.RED);
                        AudioClip error = new AudioClip(new File("src/source/error.mp3").toURI().toString());
                        error.play();
                    }else {
                        Files.writeString(Path.of(fileName), encryptedFileBody);
                        ecnrypted.setText("Your file has been encrypted");
                        ecnrypted.setFill(Color.BROWN);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

        ButtonsWrapper.getChildren().add(encrypt);
        ButtonsWrapper.getChildren().add(save);

        pane.getColumnConstraints().add(new ColumnConstraints(800));
        pane.getRowConstraints().add(new RowConstraints(75));
        pane.getRowConstraints().add(new RowConstraints(75));
        pane.getRowConstraints().add(new RowConstraints(60));
        pane.getRowConstraints().add(new RowConstraints(60));

        GridPane.setHalignment(wordToEncrypt, HPos.CENTER);
        GridPane.setHalignment(key, HPos.CENTER);
        GridPane.setHalignment(ButtonsWrapper, HPos.RIGHT);
        GridPane.setHalignment(ecnrypted, HPos.LEFT);

        pane.setPadding(new Insets(100, 150, 100, 150));

        pane.add(wordInputWrapper, 0, 0);
        pane.add(keyInputWrapper, 0, 1);
        pane.add(ButtonsWrapper, 0, 2);
        pane.add(ecnrypted, 0, 3);

        ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Info about student");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Main.newWindow.setTitle("Info about student");
                Main.newWindow.setScene(InfoWindow.returnDialogScene());
                Main.newWindow.setX(Main.primaryStage.getX() + 200);
                Main.newWindow.setY(Main.primaryStage.getY() + 100);
                Main.newWindow.show();
            }
        });
        MenuItem item2 = new MenuItem("Info about algorithm");
        item2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Main.newWindow.setTitle("Info about algorithm");
                Main.newWindow.setScene(InfoAlgWindow.returnDialogScene());
                Main.newWindow.setX(Main.primaryStage.getX() + 200);
                Main.newWindow.setY(Main.primaryStage.getY() + 100);
                Main.newWindow.show();
            }
        });

        contextMenu.getItems().addAll(item1, item2);
        dialogGroup.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                System.out.println("contextMenu");
                contextMenu.show(dialogGroup, event.getScreenX(), event.getScreenY());
            }
        });

        dialogGroup.getChildren().add(pane);
        dialogGroup.getChildren().add(buttonPos);
    }



    public Scene returnDialogScene(){
        return new Scene(dialogGroup, 1280, 720, Color.BEIGE);
    }

}
