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

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DialogWindow {
//    public Dialog<String> dialog = new Dialog<String>();
    private Group dialogGroup;
    private Label labelWindow;
    private HBox hbox;
    private Label descr;
    private Label studentInfo;
    private GridPane pane;
    private Image avatarImage;
    private ImageView avatarIV;

    public DialogWindow() throws FileNotFoundException {
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24);
        Font fontInfo = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 18);
        Font fontDescr = Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 14);
        dialogGroup = new Group();
        pane = new GridPane();

        labelWindow = new Label("Система шифрування Віженера");
        labelWindow.setFont(font);

        hbox = new HBox();

        avatarImage = new Image(new FileInputStream("src/source/avatar.jpeg"));
        avatarIV = new ImageView(avatarImage);
        avatarIV.setPreserveRatio(true);
        avatarIV.setFitHeight(150.0);
        avatarIV.setFitWidth(100.0);
        avatarIV.setStyle("-fx-margin: 15px;");

        descr = new Label();
        descr.setText("Даний шифр багатоалфавітної заміни можна описати таблицею шифрування. Для російського алфавіту вона має\n" +
                "вигляд, показаний у додатка Д. Таблиця Віжинера використовується  для шифрування і розшифрування.\n" +
                "Таблиця має три входи:\n" +
                "- верхній рядок підкреслених символів, що застосовується для зчитування чергової літери \n" +
                "- початкового відкритого тексту\n" +
                "- крайній лівий стовпець ключа\n" +
                "Послідовність ключів зазвичай отримують з числових значень літер ключового слова. При шифруванні початкового\n" +
                "повідомлення його вписують в рядок, а під ним записують ключове слово або фразу. Якщо ключ виявився коротшим,\n" +
                "ніж повідомлення, то його циклічно повторюють. В процесі шифрування знаходять у верхньому рядку таблиці чергову\n" +
                "букву початкового тексту і в лівому стовпці чергове значення ключа. Чергова літера шифротексту знаходиться на\n" +
                "перетині стовпця, що визначається літерою, яка шифрується, і рядка, що відповідає значенню ключа. ");
        descr.setFont(fontDescr);

        studentInfo = new Label();
        studentInfo.setText("    ");
        studentInfo.setFont(fontInfo);
        Button button = new Button("Confirm");

        hbox.getChildren().addAll(avatarIV, studentInfo);

        pane.getColumnConstraints().add(new ColumnConstraints(900));
        pane.getRowConstraints().add(new RowConstraints(100));
        pane.getRowConstraints().add(new RowConstraints(100));
        pane.getRowConstraints().add(new RowConstraints(250));
        pane.getRowConstraints().add(new RowConstraints(50));

        GridPane.setHalignment(button, HPos.RIGHT);
        GridPane.setHalignment(labelWindow, HPos.CENTER);
        GridPane.setHalignment(descr, HPos.CENTER);
        GridPane.setHalignment(hbox, HPos.CENTER);

        hbox.setAlignment(Pos.CENTER_LEFT);



        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                System.out.println("button click");
            }
        });
        pane.setPadding(new Insets(50, 50, 50, 50));
        pane.add(labelWindow, 0, 0);
        pane.add(hbox, 0, 1);
        pane.add(descr, 0, 2);
        pane.add(button, 0, 3);


        dialogGroup.getChildren().add(pane);
    }



    public Scene returnDialogScene(){
        return new Scene(dialogGroup, 1280, 720, Color.BEIGE);
    }

}
