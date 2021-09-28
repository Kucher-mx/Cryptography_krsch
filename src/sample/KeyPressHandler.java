package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class KeyPressHandler implements EventHandler<KeyEvent>{

    @Override
    public void handle(KeyEvent event) {
        for(int i = 0; i < Main.microObjectsCT.size(); i++){
            if(Main.microObjectsCT.size() > 0){
                if(Main.microObjectsCT.get(i).getActive()){
                    if (event.getCode().equals(KeyCode.W) && !event.isShiftDown()){
                        Main.microObjectsCT.get(i).run(0,-1);
                    }

                    if (event.getCode().equals(KeyCode.D)){
                        Main.microObjectsCT.get(i).run(1,0);
                    }

                    if (event.getCode().equals(KeyCode.S)){
                        Main.microObjectsCT.get(i).run(0,1);
                    }

                    if (event.getCode().equals(KeyCode.A)){
                        Main.microObjectsCT.get(i).run(-1,0);
                    }
                }
            }
        }

        for(int i = 0; i < Main.microObjectsT.size(); i++){
            if(Main.microObjectsT.size() > 0){
                if(Main.microObjectsT.get(i).getActive()){
                    if (event.getCode().equals(KeyCode.W)){
                        Main.microObjectsT.get(i).run(0,-1);
                    }

                    if (event.getCode().equals(KeyCode.D)){
                        Main.microObjectsT.get(i).run(1,0);
                    }

                    if (event.getCode().equals(KeyCode.S)){
                        Main.microObjectsT.get(i).run(0,1);
                    }

                    if (event.getCode().equals(KeyCode.A)){
                        Main.microObjectsT.get(i).run(-1,0);
                    }
                }
            }
        }

        if (event.getCode().equals(KeyCode.I)){
            Dialog<Boolean> dialog = new Dialog<>();
            dialog.setTitle("Spawn Micro");
            Button button = new Button("Spawn");
            Label spawnLabel = new Label();
            spawnLabel.setText("Choose characteristics of the unit:");

            RadioButton first = new RadioButton("First level");
            RadioButton second = new RadioButton("Second level");
            RadioButton third = new RadioButton("Third level");

            ToggleGroup radioGroupLvl = new ToggleGroup();
            first.setToggleGroup(radioGroupLvl);
            second.setToggleGroup(radioGroupLvl);
            third.setToggleGroup(radioGroupLvl);

            RadioButton tSide = new RadioButton("t side");
            RadioButton ctSide = new RadioButton("ct side");

            ToggleGroup radioGroupSide = new ToggleGroup();
            tSide.setToggleGroup(radioGroupSide);
            ctSide.setToggleGroup(radioGroupSide);

            GridPane dialogPane = new GridPane();

            dialogPane.getRowConstraints().add(new RowConstraints(50));
            dialogPane.getRowConstraints().add(new RowConstraints(50));
            dialogPane.getRowConstraints().add(new RowConstraints(50));

            dialogPane.getColumnConstraints().add(new ColumnConstraints(100));
            dialogPane.getColumnConstraints().add(new ColumnConstraints(100));
            dialogPane.getColumnConstraints().add(new ColumnConstraints(100));

            dialogPane.add(spawnLabel, 0, 0, 3, 1);
            dialogPane.add(tSide, 0, 1);
            dialogPane.add(ctSide, 1, 1);
            dialogPane.add(button, 2, 1);
            dialogPane.add(first, 0, 2);
            dialogPane.add(second, 1, 2);
            dialogPane.add(third, 2, 2);

            dialogPane.setHalignment(spawnLabel, HPos.CENTER);
            dialogPane.setValignment(spawnLabel, VPos.CENTER);
            dialogPane.setHalignment(button, HPos.CENTER);
            dialogPane.setValignment(button, VPos.CENTER);

            dialogPane.setStyle("-fx-padding: 35px 0 0 40px;");

            dialogPane.setHgap(10);
            dialogPane.setVgap(12);

            dialog.getDialogPane().setPrefSize(400, 250);
            dialog.getDialogPane().getChildren().add(dialogPane);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    RadioButton lvlRadioValue = (RadioButton)radioGroupLvl.getSelectedToggle();
                    RadioButton sideRadioValue = (RadioButton)radioGroupSide.getSelectedToggle();
                    MicroObject createdMicro = null;
                    try {
                        if(sideRadioValue.getText().equals("ct side")){
                            if(lvlRadioValue.getText().equals("First level")){
                                createdMicro = new MicroObject("ct");
                            }else if(lvlRadioValue.getText().equals("Second level")){
                                createdMicro = new MicroObjectOne("ct");
                            }else if(lvlRadioValue.getText().equals("Third level")){
                                createdMicro = new MicroObjectTwo("ct");
                            }
                            Main.microObjectsCT.add(createdMicro);
                        }else if(sideRadioValue.getText().equals("t side")){
                            if(lvlRadioValue.getText().equals("First level")){
                                createdMicro = new MicroObject("t");
                            }else if(lvlRadioValue.getText().equals("Second level")){
                                createdMicro = new MicroObjectOne("t");
                            }else if(lvlRadioValue.getText().equals("Third level")){
                                createdMicro = new MicroObjectTwo("t");
                            }
                            Main.microObjectsT.add(createdMicro);
                        }else {
                            spawnLabel.setText("Choose char.");
                            dialog.show();
                        }
                        Main.minimap.addUnit(createdMicro);
                        Main.group.getChildren().add(createdMicro.microGroup);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    dialog.setResult(Boolean.TRUE);
                }
            });

            dialog.show();
        }

        if (event.getCode().equals(KeyCode.R)){
            ArrayList<MicroObject> microsToDelete = new ArrayList<MicroObject>();
            for(MicroObject unit : Main.microObjectsCT){
                if(unit.getActive()){
                    microsToDelete.add(unit);
                    Main.showInfoActive(unit, false);
                    Main.minimap.deleteUnit(unit);
                }
            }

            for(MicroObject unit : Main.microObjectsT){
                if(unit.getActive()){
                    microsToDelete.add(unit);
                    Main.showInfoActive(unit, false);
                    Main.minimap.deleteUnit(unit);
                }
            }

            for(MicroObject unitToDelete : microsToDelete){
                Main.group.getChildren().remove(unitToDelete.microGroup);
                if(unitToDelete.getSide().equals("ct")){
                    Main.microObjectsCT.remove(unitToDelete);
                }else{
                    Main.microObjectsT.remove(unitToDelete);
                }
            }
        }

        if (event.getCode().equals(KeyCode.ESCAPE)){
            for(MicroObject unit : Main.microObjectsCT){
                if(unit.getActive()){
                    unit.changeActive();
                    unit.microWrapper.setStyle(" ");
                    Main.showInfoActive(unit, false);
                }
            }

            for(MicroObject unit : Main.microObjectsT){
                if(unit.getActive()){
                    unit.changeActive();
                    unit.microWrapper.setStyle(" ");
                    Main.showInfoActive(unit, false);
                }
            }
        }

        if (event.getCode().equals(KeyCode.C)){
            for(MicroObject unit : Main.microObjectsCT){
                    int rndSite = Main.random.nextInt(100);
                    if(rndSite % 2 == 0){
                        unit.setXCoordDest(Main.random.nextInt(50) + 2820);
                        unit.setYCoordDest(Main.random.nextInt(30) + 420);
                    }else{
                        unit.setXCoordDest(Main.random.nextInt(50) + 600);
                        unit.setYCoordDest(Main.random.nextInt(30) + 230);
                    }
                    Main.toMacro = true;
                    Main.timeToCapture = 5000;
            }

            for(MicroObject unit : Main.microObjectsT){
                    int rndSite = Main.random.nextInt(100);
                    if(rndSite % 2 == 0){
                        unit.setXCoordDest(Main.random.nextInt(50) + 2820);
                        unit.setYCoordDest(Main.random.nextInt(30) + 420);
                    }else{
                        unit.setXCoordDest(Main.random.nextInt(50) + 600);
                        unit.setYCoordDest(Main.random.nextInt(30) + 230);
                    }
                    Main.toMacro = true;
                    Main.timeToCapture = 5000;
                }
        }

        if (event.getCode().equals(KeyCode.B)){
            Main.berserkPressed = true;
            for(MicroObject micro : Main.microObjectsCT){
                micro.setDamage(micro.defaultDamage * 3);
                micro.microLabel.setStyle("-fx-border-color: blue; -fx-padding: 3px");
            }

            for(MicroObject micro : Main.microObjectsT){
                micro.setDamage(micro.defaultDamage * 3);
                micro.microLabel.setStyle("-fx-border-color: blue; -fx-padding: 3px");
            }
        }

        if (event.getCode().equals(KeyCode.K)){
            Dialog<Boolean> dialog = new Dialog<>();
            dialog.setTitle("Key handling info");
            Button button = new Button("OK");
            Label V = new Label("V - use ability of 2&3 lvl units");
            Label B = new Label("B - use berserk mode (All units)");
            Label R = new Label("R - remove selected units");
            Label C = new Label("C - sent all units to macro");
            Label ESCAPE = new Label("ESCAPE - cancel selection");
            Label I = new Label("I - insert an unit");
            Label N = new Label("N - clone selected unit/units");
            Label M = new Label("M - print units in sites, if they are (utils))");
            Label WASD = new Label("WASD - move active unit/units");

            GridPane dialogPane = new GridPane();

            dialogPane.getRowConstraints().add(new RowConstraints(30));
            dialogPane.getRowConstraints().add(new RowConstraints(30));
            dialogPane.getRowConstraints().add(new RowConstraints(30));
            dialogPane.getRowConstraints().add(new RowConstraints(30));
            dialogPane.getRowConstraints().add(new RowConstraints(30));
            dialogPane.getRowConstraints().add(new RowConstraints(30));
            dialogPane.getRowConstraints().add(new RowConstraints(30));
            dialogPane.getRowConstraints().add(new RowConstraints(30));
            dialogPane.getRowConstraints().add(new RowConstraints(30));
            dialogPane.getRowConstraints().add(new RowConstraints(30));

            dialogPane.getColumnConstraints().add(new ColumnConstraints(250));

            dialogPane.add(WASD, 0, 0);
            dialogPane.add(ESCAPE, 0, 1);
            dialogPane.add(R, 0, 2);
            dialogPane.add(I, 0, 3);
            dialogPane.add(C, 0, 4);
            dialogPane.add(V, 0, 5);
            dialogPane.add(B, 0, 6);
            dialogPane.add(N, 0, 7);
            dialogPane.add(M, 0, 8);
            dialogPane.add(button, 0, 9);

            dialogPane.setHalignment(WASD, HPos.CENTER);
            dialogPane.setValignment(WASD, VPos.CENTER);
            dialogPane.setHalignment(ESCAPE, HPos.CENTER);
            dialogPane.setValignment(ESCAPE, VPos.CENTER);
            dialogPane.setHalignment(R, HPos.CENTER);
            dialogPane.setValignment(R, VPos.CENTER);
            dialogPane.setHalignment(I, HPos.CENTER);
            dialogPane.setValignment(I, VPos.CENTER);
            dialogPane.setHalignment(C, HPos.CENTER);
            dialogPane.setValignment(C, VPos.CENTER);
            dialogPane.setHalignment(V, HPos.CENTER);
            dialogPane.setValignment(V, VPos.CENTER);
            dialogPane.setHalignment(B, HPos.CENTER);
            dialogPane.setValignment(B, VPos.CENTER);
            dialogPane.setHalignment(N, HPos.CENTER);
            dialogPane.setValignment(N, VPos.CENTER);
            dialogPane.setHalignment(M, HPos.CENTER);
            dialogPane.setValignment(M, VPos.CENTER);
            dialogPane.setHalignment(button, HPos.RIGHT);
            dialogPane.setValignment(button, VPos.CENTER);

            dialogPane.setStyle("-fx-padding: 15px 0 0 75px;");

            dialog.getDialogPane().setPrefSize(430, 330);
            dialog.getDialogPane().getChildren().add(dialogPane);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    dialog.setResult(Boolean.TRUE);
                }
            });

            dialog.showAndWait();
        }

        if (event.getCode().equals(KeyCode.N)){
            ArrayList<MicroObject> clones = new ArrayList<MicroObject>();
            MicroObject tmp;

            for(MicroObject unit : Main.microObjectsCT) {
                if (unit.getActive()) {
                    try {
                        tmp  = (MicroObject) unit.clone();
                        clones.add(tmp);
                    } catch (CloneNotSupportedException exception) {
                        System.out.println("clone exception: " + exception);
                    }
                }
            }

            for(MicroObject unit : Main.microObjectsT) {
                if (unit.getActive()) {
                    try {
                        tmp  = (MicroObject) unit.clone();
                        clones.add(tmp);
                    } catch (CloneNotSupportedException exception) {
                        System.out.println("clone exception: " + exception);
                    }
                }
            }

            if (!clones.isEmpty()){
                for (MicroObject unit : clones) {
                    if (unit.getSide().equals("ct"))
                        Main.microObjectsCT.add(unit);
                    if (unit.getSide().equals("t"))
                        Main.microObjectsT.add(unit);
                    try {
                        Main.minimap.addUnit(unit);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    Main.group.getChildren().add(unit.microGroup);
                }
            }


        }

        if (event.getCode().equals(KeyCode.M)){
            for (MacroObjSite site : Main.sites){
                System.out.println("print site: " + site.getName());
                site.printTeamCT();
                site.printTeamT();
                System.out.println();
            }
        }

        if (event.getCode().equals(KeyCode.V)){
            Main.secondLvlAbility = true;
        }

        if(event.getCode().equals(KeyCode.X)){
            Dialog<Boolean> dialog = new Dialog<>();
            dialog.setTitle("Def kr task");
            Button ok = new Button("OK");
            Button cancel = new Button("CANCEL");

            GridPane dialogPane = new GridPane();
            dialogPane.getColumnConstraints().add(new ColumnConstraints(400));

            GridPane dialogPane2 = new GridPane();
            dialogPane2.getColumnConstraints().add(new ColumnConstraints(400));

            ToggleGroup tGroup = new ToggleGroup();
            ToggleGroup ctGroup = new ToggleGroup();

            int ctIterator = 0, tIterator = 0;
            for(MicroObject micro : Main.microObjectsCT){
                HBox container = new HBox();
                dialogPane.getRowConstraints().add(new RowConstraints(30));
                Label unit = new Label(micro.toString());
                RadioButton radio = new RadioButton(String.valueOf(micro.id));
                radio.setToggleGroup(ctGroup);
                container.getChildren().addAll(radio, unit);
                dialogPane.add(container, 0, ctIterator);
                ctIterator++;
            }

            for(MicroObject micro : Main.microObjectsT){
                HBox container = new HBox();
                dialogPane2.getRowConstraints().add(new RowConstraints(30));
                Label unit = new Label(micro.toString());
                RadioButton radio = new RadioButton(String.valueOf(micro.id));
                radio.setToggleGroup(tGroup);
                container.getChildren().addAll(radio, unit);
                dialogPane2.add(container, 0, tIterator);
                tIterator++;
            }

            System.out.println(tIterator + " " + ctIterator);

            dialogPane.add(cancel, 0, ctIterator);
            dialogPane2.add(ok, 0, tIterator);

            dialogPane2.setStyle("-fx-padding: 15; -fx-spacing: 10;");
            dialogPane.setStyle("-fx-padding: 15; -fx-spacing: 10;");

            dialog.getDialogPane().setPrefSize(1000, 400);
            dialog.getDialogPane().getChildren().addAll(new HBox(dialogPane, dialogPane2));
            dialogPane.setStyle("-fx-padding: 70px 0 0 150px;");
            dialogPane2.setStyle("-fx-padding: 70px 0 0 30px;");

            ok.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    RadioButton tVal = (RadioButton)tGroup.getSelectedToggle();
                    RadioButton ctVal = (RadioButton)ctGroup.getSelectedToggle();

                    Main.defKRTask(Integer.parseInt(tVal.getText()), Integer.parseInt(ctVal.getText()));
                    dialog.setResult(Boolean.TRUE);
                }
            });

            cancel.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    dialog.setResult(Boolean.FALSE);
                }
            });

            dialog.showAndWait();
        }
    }
}
