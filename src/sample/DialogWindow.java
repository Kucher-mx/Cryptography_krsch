package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.FileNotFoundException;

public class DialogWindow {
    //Creating a dialog
    public Dialog<String> dialog = new Dialog<String>();
    private Group dialogGroup;
    private Label labelWindow;
    private GridPane pane;

    public DialogWindow(){
        HBox subPane = new HBox();
        dialogGroup = new Group();
        pane = new GridPane();

        labelWindow = new Label("Enter size of the teams:");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14);
        labelWindow.setFont(font);
        TextField textField = new TextField();
        textField.maxWidth(150.0);
        Button button = new Button("Confirm");

        pane.getColumnConstraints().add(new ColumnConstraints(200));
        pane.getColumnConstraints().add(new ColumnConstraints(175));
        pane.getColumnConstraints().add(new ColumnConstraints(100));
        pane.getRowConstraints().add(new RowConstraints(100));
        GridPane.setHalignment(button, HPos.CENTER);
        pane.setGridLinesVisible(true);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (textField.getText() != null){
                    int sizeOfTheTeam = Integer.parseInt(textField.getText());
                    Main.teamSize = sizeOfTheTeam;
                    System.out.println("teamSize dialog: " + Main.teamSize);

                    pane.getChildren().clear();
                    while(pane.getRowConstraints().size() > 0){
                        pane.getRowConstraints().remove(0);
                    }

                    while(pane.getColumnConstraints().size() > 0){
                        pane.getColumnConstraints().remove(0);
                    }
                    pane.getRowConstraints().add(new RowConstraints(75));
                    pane.getRowConstraints().add(new RowConstraints(75));
                    pane.getColumnConstraints().add(new ColumnConstraints(120));

                    Label ctSite = new Label("fill ct characters: ");
                    pane.add(ctSite, 0, 0);
                    for (int i = 0; i < Main.teamSize; i++){
                        if(i < Main.teamSize - 1){
                            pane.getColumnConstraints().add(new ColumnConstraints(120));
                        }
                        pane.add(new ChoiceBox(FXCollections.observableArrayList("First level", "Second level", "Third level")), i , 1);
                    }

                    pane.getRowConstraints().add(new RowConstraints(75));
                    pane.getRowConstraints().add(new RowConstraints(75));
                    Label tSite = new Label("fill t characters: ");
                    pane.add(tSite, 0, 2);
                    for (int i = 0; i < Main.teamSize; i++){
                        pane.add(new ChoiceBox(FXCollections.observableArrayList("First level", "Second level", "Third level")), i, 3);
                    }

                    pane.getRowConstraints().add(new RowConstraints(75));
                    Button buttonMicro = new Button("Confirm");
                    pane.add(buttonMicro, 4, 4);
                    buttonMicro.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent e) {
                            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                            double  width = screenSize.getWidth();
                            double height = screenSize.getHeight();
                            try {
                                Main.setMainStage(width, height);
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }
                        }
                    });
                } else {
                    labelWindow.setText("Enter the right data to the teams' size.");
                }
            }
        });

        pane.setPadding(new Insets(1280/6, 720/2, 1280/4, 720/2));
//        subPane.getChildren().addAll(labelWindow, textField, button);
        pane.add(labelWindow, 0, 0);
        pane.add(textField, 1, 0);
        pane.add(button, 2, 0);
        dialogGroup.getChildren().add(pane);
    }



    public Scene returnDialogScene(){
        Scene scene = new Scene(dialogGroup, 1280, 720, Color.BEIGE);
        return scene;
    }

}
