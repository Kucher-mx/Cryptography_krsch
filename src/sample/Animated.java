package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Animated {

    public void show() {
        GridPane pane = new GridPane();
        Text msg = new Text("Вас вітає Назарій Людва");
        msg.setTextOrigin(VPos.TOP);
        msg.setFont(Font.font(24));

        double msgWidth = msg.getLayoutBounds().getWidth();
        double sceneWidth = Main.primaryStage.getWidth();

        KeyValue initKeyValue = new KeyValue(msg.translateXProperty(), sceneWidth);
        KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);

        KeyValue endKeyValue = new KeyValue(msg.translateXProperty(), -1.0
                * msgWidth);
        KeyFrame endFrame = new KeyFrame(Duration.seconds(3), endKeyValue);

        Timeline timeline = new Timeline(initFrame, endFrame);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Button next = new Button("Confirm");

        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Main.setSetupStage();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        HBox wrapper = new HBox();

        GridPane.setHalignment(msg, HPos.CENTER);
        GridPane.setHalignment(next, HPos.RIGHT);

        pane.getColumnConstraints().add(new ColumnConstraints(300));
        pane.getRowConstraints().add(new RowConstraints(40));
        pane.getRowConstraints().add(new RowConstraints(60));

        pane.add(msg, 0, 0);
        pane.add(next, 0, 1);
        wrapper.getChildren().add(pane);
        wrapper.setLayoutY(80);
        Main.primaryStage.setScene( new Scene(new Group(wrapper), 600, 300, Color.BEIGE));
    }
}
