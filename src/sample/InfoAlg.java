package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InfoAlg {
    public static Group dialogGroup = new Group();
    private Label labelWindow;
    private Label descr;
    private Label vijenerImageLabel;
    private Image vijenerImage;
    private ImageView vijenerIV;
    public static VBox vijenerImageWrapper = new VBox();
    public static GridPane pane = new GridPane();
    public InfoAlg() throws FileNotFoundException {
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14);
        Font fontDescr = Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 14);
        labelWindow = new Label("Система шифрування Віженера");
        labelWindow.setFont(font);

        vijenerImageLabel = new Label("                                             Рис 1");
        vijenerImageLabel.setFont(font);

        vijenerImage = new Image(new FileInputStream("src/source/encrypt_example.png"));
        vijenerIV = new ImageView(vijenerImage);
        vijenerIV.setPreserveRatio(true);
        vijenerIV.setFitWidth(450.0);
        vijenerImageWrapper.setLayoutX(1000);
        vijenerImageWrapper.setLayoutY(150);

        descr = new Label();
        descr.setText("Даний шифр багатоалфавітної заміни можна описати таблицею шифрування. Для латинського алфавіту вона має\n" +
                "вигляд, показаний на Рис 1. Таблиця Віжинера використовується  для шифрування і розшифрування.\n" +
                "Таблиця має два входи:\n" +
                "- верхній рядок підкреслених символів, що застосовується для зчитування чергової літери " +
                "початкового відкритого тексту\n" +
                "- крайній лівий стовпець ключа\n" +
                "Послідовність ключів зазвичай отримують з числових значень літер ключового слова. При шифруванні початкового\n" +
                "повідомлення його вписують в рядок, а під ним записують ключове слово або фразу. Якщо ключ виявився коротшим,\n" +
                "ніж повідомлення, то його циклічно повторюють. В процесі шифрування знаходять у верхньому рядку таблиці чергову\n" +
                "букву початкового тексту і в лівому стовпці чергове значення ключа. Чергова літера шифротексту знаходиться на\n" +
                "перетині стовпця, що визначається літерою, яка шифрується, і рядка, що відповідає значенню ключа. ");
        descr.setFont(fontDescr);

        Button button = new Button("Підтвердити");


        pane.getColumnConstraints().add(new ColumnConstraints(900));
        pane.getRowConstraints().add(new RowConstraints(100));
        pane.getRowConstraints().add(new RowConstraints(250));
        pane.getRowConstraints().add(new RowConstraints(50));

        GridPane.setHalignment(button, HPos.RIGHT);
        GridPane.setHalignment(labelWindow, HPos.CENTER);
        GridPane.setHalignment(descr, HPos.CENTER);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Main.newWindow.close();
                InfoDialog.dialogGroup = new Group();
                InfoDialog.dialogGroup.getChildren().add(InfoDialog.pane);
                dialogGroup = new Group();
                dialogGroup.getChildren().add(pane);
                dialogGroup.getChildren().add(vijenerImageWrapper);
            }
        });
        pane.setPadding(new Insets(50, 50, 50, 50));
        pane.add(labelWindow, 0, 0);
        pane.add(descr, 0, 1);
        pane.add(button, 0, 2);

        vijenerImageWrapper.getChildren().add(vijenerIV);
        vijenerImageWrapper.getChildren().add(vijenerImageLabel);

        dialogGroup.getChildren().add(pane);
        dialogGroup.getChildren().add(vijenerImageWrapper);
    }

    public Scene returnDialogScene(){
        return new Scene(dialogGroup, 1480, 520, Color.BEIGE);
    }
}
