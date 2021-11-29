package sample;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.util.Duration;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class DialogWindow {
    private Group dialogGroup;
    private GridPane pane;
    private TextField wordToEncrypt = new TextField();
    private TextField key = new TextField();
    private Button encrypt = new Button("Зашифрувати");
    private Button dencrypt = new Button("Дешифрувати");
    private Text ecnrypted;
    private Label wordLabel;
    private Label keyLabel;
    private InfoDialog InfoWindow = new InfoDialog();
    private InfoAlg InfoAlgWindow = new InfoAlg();
    private HBox wordInputWrapper = new HBox();
    private HBox keyInputWrapper = new HBox();
    private HBox ButtonsWrapper = new HBox();
    private VBox MenuWrapper = new VBox();
    private Button encryptFile = new Button("Зашифрувати файл");
    private Button dencryptFile = new Button("Дешифрувати файл");
    private Encrypt encryptinst = new Encrypt();
    private ColorPicker colorPicker = new ColorPicker();

    public DialogWindow() throws FileNotFoundException {

        Font fontEncrypted = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16);
        dialogGroup = new Group();
        pane = new GridPane();

        Menu fileMenu = new Menu("File");
        MenuItem menuItem1 = new MenuItem("Інформація про студента");

        menuItem1.setOnAction(e -> {
            Main.newWindow.setTitle("Second Stage");
            Main.newWindow.setScene(InfoWindow.returnDialogScene());
            Main.newWindow.setX(Main.primaryStage.getX() + 200);
            Main.newWindow.setY(Main.primaryStage.getY() + 100);
            Main.newWindow.show();
        });

        MenuItem menuItem2 = new MenuItem("Інформація про алгоритм");

        menuItem2.setOnAction(e -> {
            Main.newWindow.setTitle("Second Stage");
            Main.newWindow.setScene(InfoAlgWindow.returnDialogScene());
            Main.newWindow.setX(Main.primaryStage.getX() + 200);
            Main.newWindow.setY(Main.primaryStage.getY() + 100);
            Main.newWindow.show();
        });

        MenuItem menuItem3 = new MenuItem("Вийти");

        menuItem3.setOnAction(e -> {
            System.exit(200);
        });

        fileMenu.getItems().add(menuItem1);
        fileMenu.getItems().add(menuItem2);
        fileMenu.getItems().add(menuItem3);

        MenuBar menuBar = new MenuBar();

        Menu options = new Menu("Options");

        MenuItem optionsItem1 = new MenuItem("Сховати");

        optionsItem1.setOnAction(e -> {
            FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(500), pane);
            fadeOutTransition.setFromValue(1.0);
            fadeOutTransition.setToValue(0.0);
            fadeOutTransition.play();
        });

        MenuItem optionsItem2 = new MenuItem("Показати");

        optionsItem2.setOnAction(e -> {
            FadeTransition fadeInTransition = new FadeTransition(Duration.millis(1500), pane);
            fadeInTransition.setFromValue(0.0);
            fadeInTransition.setToValue(1.0);
            fadeInTransition.play();
        });

        MenuItem optionsItem3 = new MenuItem("Вибрати колір фону");

        optionsItem3.setOnAction(e -> {
            pane.add(colorPicker, 0, 4);
        });

        options.getItems().add(optionsItem2);
        options.getItems().add(optionsItem1);
        options.getItems().add(optionsItem3);

        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(options);
        MenuWrapper.getChildren().add(menuBar);
        MenuWrapper.setLayoutX(0);
        MenuWrapper.setLayoutY(0);
        MenuWrapper.setPrefWidth(1000);

        wordToEncrypt.setPrefWidth(500.0);
        key.setPrefWidth(500.0);
        ecnrypted = new Text(0, 0,"Зашифрованний текст: ");
        ecnrypted.setWrappingWidth(750);
        ecnrypted.setFont(fontEncrypted);

        wordLabel = new Label("Введіть текст: ");
        wordLabel.setFont(fontEncrypted);

        keyLabel = new Label("Введіть ключ: ");
        keyLabel.setFont(fontEncrypted);

        encrypt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(wordToEncrypt.getText() == "" || key.getText() == "" ||
                    Pattern.matches(".*\\p{InCyrillic}.*", key.getText()) ||
                    Pattern.matches(".*\\p{InCyrillic}.*", wordToEncrypt.getText())){
                    AudioClip error = new AudioClip(new File("src/source/error.mp3").toURI().toString());
                    error.play();
                    ecnrypted.setText("Ви ввели не правильні данні");
                    ecnrypted.setFill(Color.RED);
                }else if(wordToEncrypt.getText().length() >= 100 || key.getText().length()  >= 100) {
                    String EWord = wordToEncrypt.getText();
                    String KWord = key.getText();
                    String encrypted = encryptinst.encrypt(EWord, KWord);
                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter("cipher.txt");
                        myWriter.write(encrypted);
                        myWriter.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    ecnrypted.setText("Було зашифровано у файл cipher.txt, оскільки текст перевищував 100 симолів (Файл знаходиться у корневій папці проекту)");
                    ecnrypted.setFill(Color.GREEN);
                }else {
                    String EWord = wordToEncrypt.getText();
                    String KWord = key.getText();
                    String encrypted = encryptinst.encrypt(EWord, KWord);
                    ecnrypted.setText("Зашифрований текст: " + encrypted);
                    ecnrypted.setFill(Color.color(0, 0, 0));
                }
            }
        });

        dencrypt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(wordToEncrypt.getText() == "" || key.getText() == "" ||
                        Pattern.matches(".*\\p{InCyrillic}.*", key.getText()) ||
                        Pattern.matches(".*\\p{InCyrillic}.*", wordToEncrypt.getText())){
                    AudioClip error = new AudioClip(new File("src/source/error.mp3").toURI().toString());
                    error.play();
                    ecnrypted.setText("Ви ввели не правильні данні");
                    ecnrypted.setFill(Color.RED);
                }else if(wordToEncrypt.getText().length() >= 100 || key.getText().length()  >= 100) {
                    String EWord = wordToEncrypt.getText();
                    String KWord = key.getText();
                    String encrypted = encryptinst.encrypt(EWord, KWord);
                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter("cipher.txt");
                        myWriter.write(encrypted);
                        myWriter.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    ecnrypted.setText("Було дешифровано у файл cipher.txt, оскільки текст перевищував 100 симолів (Файл знаходиться у корневій папці проекту)");
                    ecnrypted.setFill(Color.GREEN);
                }else {
                    String EWord = wordToEncrypt.getText();
                    String KWord = key.getText();
                    String encrypted = encryptinst.dencrypt(EWord, KWord);
                    ecnrypted.setText("Дешифрований текст: " + encrypted);
                    ecnrypted.setFill(Color.color(0, 0, 0));
                }
            }
        });

        HBox buttonPos = new HBox();
        buttonPos.setLayoutX(25.0);
        buttonPos.setLayoutY(15.0);

        encryptFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Open file to encrypt");
                    File file = fileChooser.showOpenDialog(Main.primaryStage);
                    String fileName = file.getAbsolutePath();
                    String fileBody = Files.readString(Path.of(fileName));

                    String KWord = key.getText();

                    if(key.getText() == "" || Pattern.matches(".*\\p{InCyrillic}.*", key.getText())){
                        ecnrypted.setText("Enter a valid key");
                        ecnrypted.setFill(Color.RED);
                        AudioClip error = new AudioClip(new File("src/source/error.mp3").toURI().toString());
                        error.play();
                    }else {
                        String encryptedFileBody = encryptinst.encrypt(fileBody, KWord);
                        Files.writeString(Path.of(fileName), encryptedFileBody);
                        ecnrypted.setText("Ваш файл було зашифровано");
                        ecnrypted.setFill(Color.GREEN);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        dencryptFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Відкрити файл для дешифрування");
                    File file = fileChooser.showOpenDialog(Main.primaryStage);
                    String fileName = file.getAbsolutePath();
                    String fileBody = Files.readString(Path.of(fileName));

                    String KWord = key.getText();

                    if(key.getText() == "" || Pattern.matches(".*\\p{InCyrillic}.*", key.getText())){
                        ecnrypted.setText("Enter a valid key");
                        ecnrypted.setFill(Color.RED);
                        AudioClip error = new AudioClip(new File("src/source/error.mp3").toURI().toString());
                        error.play();
                    }else {
                        String dencryptedFileBody = encryptinst.dencrypt(fileBody, KWord);
                        Files.writeString(Path.of(fileName), dencryptedFileBody);
                        ecnrypted.setText("Ваш файл було дешифровано");
                        ecnrypted.setFill(Color.GREEN);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        wordInputWrapper.getChildren().add(wordLabel);
        wordInputWrapper.getChildren().add(wordToEncrypt);

        keyInputWrapper.getChildren().add(keyLabel);
        keyInputWrapper.getChildren().add(key);

        ButtonsWrapper.getChildren().add(encrypt);
        ButtonsWrapper.getChildren().add(dencrypt);
        ButtonsWrapper.getChildren().add(encryptFile);
        ButtonsWrapper.getChildren().add(dencryptFile);


        pane.getColumnConstraints().add(new ColumnConstraints(800));
        pane.getRowConstraints().add(new RowConstraints(75));
        pane.getRowConstraints().add(new RowConstraints(75));
        pane.getRowConstraints().add(new RowConstraints(60));
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

        MenuItem item1 = new MenuItem("Інформація про студента");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Main.newWindow.setTitle("Інформація про студента");
                Main.newWindow.setScene(InfoWindow.returnDialogScene());
                Main.newWindow.setX(Main.primaryStage.getX() + 200);
                Main.newWindow.setY(Main.primaryStage.getY() + 100);
                Main.newWindow.show();

            }
        });
        MenuItem item2 = new MenuItem("Інформація про алгоритм");
        item2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Main.newWindow.setTitle("Інформація про алгоритм");
                Main.newWindow.setScene(InfoAlgWindow.returnDialogScene());
                Main.newWindow.setX(Main.primaryStage.getX() + 200);
                Main.newWindow.setY(Main.primaryStage.getY() + 100);
                Main.newWindow.show();
            }
        });

        MenuItem item3 = new MenuItem("Показати");
        item3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FadeTransition fadeInTransition = new FadeTransition(Duration.millis(1500), pane);
                fadeInTransition.setFromValue(0.0);
                fadeInTransition.setToValue(1.0);
                fadeInTransition.play();
            }
        });

        MenuItem item4 = new MenuItem("Сховати");
        item4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(500), pane);
                fadeOutTransition.setFromValue(1.0);
                fadeOutTransition.setToValue(0.0);
                fadeOutTransition.play();
            }
        });

        MenuItem item5 = new MenuItem("Сховати меню");
        item5.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                contextMenu.hide();
            }
        });


        contextMenu.getItems().addAll(item1, item2, item3, item4, item5);
        dialogGroup.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(dialogGroup, event.getScreenX(), event.getScreenY());

            }
        });

        dialogGroup.getChildren().add(pane);
        dialogGroup.getChildren().add(MenuWrapper);

        colorPicker.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Main.BACKGROUND_COLOR = Color.web(
                        String.valueOf(colorPicker.getValue())
                );

                Group newGroup = new Group();
                newGroup.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
                    @Override
                    public void handle(ContextMenuEvent event) {
                        contextMenu.show(dialogGroup, event.getScreenX(), event.getScreenY());

                    }
                });
                newGroup.getChildren().add(pane);
                newGroup.getChildren().add(MenuWrapper);
                Main.primaryStage.setScene( new Scene(newGroup, 1280, 720, Main.BACKGROUND_COLOR));
                pane.getChildren().remove(colorPicker);
            }
        });
    }



    public void setDialogScene(){
        Main.primaryStage.setScene( new Scene(dialogGroup, 1280, 720, Main.BACKGROUND_COLOR));
    }



}
