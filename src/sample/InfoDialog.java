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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InfoDialog {
    public static Group dialogGroup = new Group();
    private HBox hbox;
    private Label studentInfo;
    public static GridPane pane = new GridPane();
    private Image avatarImage;
    private ImageView avatarIV;
    public InfoDialog() throws FileNotFoundException {
        Font fontInfo = Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 12);

        hbox = new HBox();

        avatarImage = new Image(new FileInputStream("src/source/avatar.jpg"));
        avatarIV = new ImageView(avatarImage);
        avatarIV.setPreserveRatio(true);
        avatarIV.setFitHeight(150.0);
        avatarIV.setFitWidth(100.0);
        avatarIV.setStyle("-fx-margin: 15px;");

        studentInfo = new Label();
        studentInfo.setText("  \t\tЛюдва Назарій Вікторович\n" + "  Вінницький Національний Технічний Університет"
                + "\n\t\t\t   ФІТКІ" +"\n\t\t     Кібербезпека" + "\n\t\t\t   2 курс");
        studentInfo.setFont(fontInfo);
        Button button = new Button("Підтвердити");

        hbox.getChildren().addAll(avatarIV, studentInfo);

        pane.getColumnConstraints().add(new ColumnConstraints(450));
        pane.getRowConstraints().add(new RowConstraints(100));
        pane.getRowConstraints().add(new RowConstraints(50));

        GridPane.setHalignment(button, HPos.RIGHT);
        GridPane.setHalignment(hbox, HPos.CENTER);

        hbox.setAlignment(Pos.CENTER_LEFT);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Main.newWindow.close();
                dialogGroup = new Group();
                dialogGroup.getChildren().add(pane);
                InfoAlg.dialogGroup = new Group();
                InfoAlg.dialogGroup.getChildren().add(InfoAlg.pane);
                InfoAlg.dialogGroup.getChildren().add(InfoAlg.vijenerImageWrapper);
            }
        });
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.add(hbox, 0, 0);
        pane.add(button, 0, 1);


        dialogGroup.getChildren().add(pane);
    }

    public Scene returnDialogScene(){
        return new Scene(dialogGroup, 500, 170, Color.BEIGE);
    }
}
