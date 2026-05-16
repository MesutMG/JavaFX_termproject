package ghosthunterinc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EditConfig extends Application {
    private static final double DEFAULT_WIDTH = 1280;
    private static final double DEFAULT_HEIGHT = 720;
    private ConfigReader config = new ConfigReader();
    private boolean isConfig = config.readConfig("config.txt");

    @Override
    public void start(Stage stage) {
        Scene scene = createScene(stage, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        stage.setTitle("Edit Config");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public Scene createScene(Stage stage, double width, double height) {
        VBox root = new VBox(20);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(40);
        gridPane.setVgap(30);
        createAllOfThoseButtonsAndEverythingButWithALoop(gridPane);

        Button saveBtn = getButton(stage);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(gridPane, saveBtn);

        return new Scene(root, width, height);
    }

    private void createAllOfThoseButtonsAndEverythingButWithALoop(GridPane root){
            double labelWidth = 200;
            double hBoxSpacing = 5;
            String fontName = "Arial";
            double fontSize = 19;
            String textColor = "black";
            String labelStyle = "-fx-font-family: '" + fontName + "'; -fx-font-size: " + fontSize + "px; -fx-text-fill: " + textColor + ";";

            String[] names = {
                    "Max Health: ", "Max Vacuum: ", "Entity Damage: ", "Vacuum Decrease: ", "Vacuum Increase: ", "Health Token Inc: ", "Vacuum Token Inc: ", "Eye Token Dur: ",
                    "L1 Area X: ", "L1 Area Y: ", "L1 Area W: ", "L1 Area H: ", "L1 Time: ", "L1 Ghosts: ", "L1 Rippers: ", "L1 Wisps: ",
                    "L2 Area X: ", "L2 Area Y: ", "L2 Area W: ", "L2 Area H: ", "L2 Time: ", "L2 Ghosts: ", "L2 Rippers: ", "L2 Wisps: ",
                    "L3 Area X: ", "L3 Area Y: ", "L3 Area W: ", "L3 Area H: ", "L3 Time: ", "L3 Ghosts: ", "L3 Rippers: ", "L3 Wisps: "
            };

            Label[] labels = new Label[32];

            for (int i = 0; i < 32; i++) {
                labels[i] = new Label(names[i] + getValue(i));
                labels[i].setPrefWidth(labelWidth);
                labels[i].setStyle(labelStyle);

                Button min = new Button("-");
                Button plus = new Button("+");

                /*using 10+ 10- for:
                max health, max vacuum, healthtoken, vacuumtoken ve level alanlari icin
                */
                int step;
                if((i == 0) || (i == 1) || (i == 5) || (i == 6) || (i >= 8 && i <= 11) || (i >= 16 && i <= 19) || (i >= 24 && i <= 27)){
                    step = 10;
                } else { step = 1; }

                int finalI = i; //intelliJ izin vermedi boyle yapiliyormus lambda'da ????
                //java muhtesem

                min.setOnAction(e -> {
                    setValue(finalI, getValue(finalI) - step);
                    labels[finalI].setText(names[finalI] + getValue(finalI));
                });

                plus.setOnAction(e -> {
                    setValue(finalI, getValue(finalI) + step);
                    labels[finalI].setText(names[finalI] + getValue(finalI));
                });

                //griddeki pozisyon
                int col = i / 8;
                int row = i % 8;
                root.add(new HBox(hBoxSpacing, labels[i], min, plus), col, row);
            }
        }

    private int getValue(int index) {
        return switch (index) {
            case 0 -> config.maximum_health;
            case 1 -> config.maximum_vacuum;
            case 2 -> config.entity_damage;
            case 3 -> config.vacuum_decrease;
            case 4 -> config.vacuum_increase;
            case 5 -> config.health_token_increase;
            case 6 -> config.vacuum_token_increase;
            case 7 -> config.eye_token_duration;
            case 8 -> config.level_1_playable_area_x;
            case 9 -> config.level_1_playable_area_y;
            case 10 -> config.level_1_playable_area_width;
            case 11 -> config.level_1_playable_area_height;
            case 12 -> config.level_1_time;
            case 13 -> config.level_1_ghosts;
            case 14 -> config.level_1_rippers;
            case 15 -> config.level_1_wisps;
            case 16 -> config.level_2_playable_area_x;
            case 17 -> config.level_2_playable_area_y;
            case 18 -> config.level_2_playable_area_width;
            case 19 -> config.level_2_playable_area_height;
            case 20 -> config.level_2_time;
            case 21 -> config.level_2_ghosts;
            case 22 -> config.level_2_rippers;
            case 23 -> config.level_2_wisps;
            case 24 -> config.level_3_playable_area_x;
            case 25 -> config.level_3_playable_area_y;
            case 26 -> config.level_3_playable_area_width;
            case 27 -> config.level_3_playable_area_height;
            case 28 -> config.level_3_time;
            case 29 -> config.level_3_ghosts;
            case 30 -> config.level_3_rippers;
            case 31 -> config.level_3_wisps;
            default -> 0;
        };
    }

    private void setValue(int index, int val) {
        switch (index) {
            case 0 -> config.maximum_health = val;
            case 1 -> config.maximum_vacuum = val;
            case 2 -> config.entity_damage = val;
            case 3 -> config.vacuum_decrease = val;
            case 4 -> config.vacuum_increase = val;
            case 5 -> config.health_token_increase = val;
            case 6 -> config.vacuum_token_increase = val;
            case 7 -> config.eye_token_duration = val;
            case 8 -> config.level_1_playable_area_x = val;
            case 9 -> config.level_1_playable_area_y = val;
            case 10 -> config.level_1_playable_area_width = val;
            case 11 -> config.level_1_playable_area_height = val;
            case 12 -> config.level_1_time = val;
            case 13 -> config.level_1_ghosts = val;
            case 14 -> config.level_1_rippers = val;
            case 15 -> config.level_1_wisps = val;
            case 16 -> config.level_2_playable_area_x = val;
            case 17 -> config.level_2_playable_area_y = val;
            case 18 -> config.level_2_playable_area_width = val;
            case 19 -> config.level_2_playable_area_height = val;
            case 20 -> config.level_2_time = val;
            case 21 -> config.level_2_ghosts = val;
            case 22 -> config.level_2_rippers = val;
            case 23 -> config.level_2_wisps = val;
            case 24 -> config.level_3_playable_area_x = val;
            case 25 -> config.level_3_playable_area_y = val;
            case 26 -> config.level_3_playable_area_width = val;
            case 27 -> config.level_3_playable_area_height = val;
            case 28 -> config.level_3_time = val;
            case 29 -> config.level_3_ghosts = val;
            case 30 -> config.level_3_rippers = val;
            case 31 -> config.level_3_wisps = val;
        }
    }

    private Button getButton(Stage stage) {
        Button saveBtn = new Button("Save And Exit");
        saveBtn.setOnAction(e -> {
            config.saveCustomConfig("config.txt");
            TitleScreen titleScreen = new TitleScreen();
            double sceneWidth = stage.getScene().getWidth();
            double sceneHeight = stage.getScene().getHeight();
            Scene titleScene = titleScreen.createScene(sceneWidth, sceneHeight, stage);
            stage.setTitle("Title Screen");
            stage.setScene(titleScene);
        });
        return saveBtn;
    }

    private void createAllOfThoseButtonsAndEverything(GridPane root){
        double labelWidth = 200;
        double hBoxSpacing = 5;
        String fontName = "Arial";
        double fontSize = 19;
        String textColor = "black";
        String labelStyle = "-fx-font-family: '" + fontName + "'; -fx-font-size: " + fontSize + "px; -fx-text-fill: " + textColor + ";";

// column 0

        Label l1 = new Label("Max Health: " + config.maximum_health);
        l1.setPrefWidth(labelWidth);
        l1.setStyle(labelStyle);
        Button min1 = new Button("-");
        Button plus1 = new Button("+");
        min1.setOnAction(e -> { config.maximum_health--; l1.setText("Max Health: " + config.maximum_health); });
        plus1.setOnAction(e -> { config.maximum_health++; l1.setText("Max Health: " + config.maximum_health); });
        root.add(new HBox(hBoxSpacing, l1, min1, plus1), 0, 0);

        Label l2 = new Label("Max Vacuum: " + config.maximum_vacuum);
        l2.setPrefWidth(labelWidth);
        l2.setStyle(labelStyle);
        Button min2 = new Button("-");
        Button plus2 = new Button("+");
        min2.setOnAction(e -> { config.maximum_vacuum--; l2.setText("Max Vacuum: " + config.maximum_vacuum); });
        plus2.setOnAction(e -> { config.maximum_vacuum++; l2.setText("Max Vacuum: " + config.maximum_vacuum); });
        root.add(new HBox(hBoxSpacing, l2, min2, plus2), 0, 1);

        Label l3 = new Label("Entity Damage: " + config.entity_damage);
        l3.setPrefWidth(labelWidth);
        l3.setStyle(labelStyle);
        Button min3 = new Button("-");
        Button plus3 = new Button("+");
        min3.setOnAction(e -> { config.entity_damage--; l3.setText("Entity Damage: " + config.entity_damage); });
        plus3.setOnAction(e -> { config.entity_damage++; l3.setText("Entity Damage: " + config.entity_damage); });
        root.add(new HBox(hBoxSpacing, l3, min3, plus3), 0, 2);

        Label l4 = new Label("Vacuum Decrease: " + config.vacuum_decrease);
        l4.setPrefWidth(labelWidth);
        l4.setStyle(labelStyle);
        Button min4 = new Button("-");
        Button plus4 = new Button("+");
        min4.setOnAction(e -> { config.vacuum_decrease--; l4.setText("Vacuum Decrease: " + config.vacuum_decrease); });
        plus4.setOnAction(e -> { config.vacuum_decrease++; l4.setText("Vacuum Decrease: " + config.vacuum_decrease); });
        root.add(new HBox(hBoxSpacing, l4, min4, plus4), 0, 3);

        Label l5 = new Label("Vacuum Increase: " + config.vacuum_increase);
        l5.setPrefWidth(labelWidth);
        l5.setStyle(labelStyle);
        Button min5 = new Button("-");
        Button plus5 = new Button("+");
        min5.setOnAction(e -> { config.vacuum_increase--; l5.setText("Vacuum Increase: " + config.vacuum_increase); });
        plus5.setOnAction(e -> { config.vacuum_increase++; l5.setText("Vacuum Increase: " + config.vacuum_increase); });
        root.add(new HBox(hBoxSpacing, l5, min5, plus5), 0, 4);

        Label l6 = new Label("Health Token Inc: " + config.health_token_increase);
        l6.setPrefWidth(labelWidth);
        l6.setStyle(labelStyle);
        Button min6 = new Button("-");
        Button plus6 = new Button("+");
        min6.setOnAction(e -> { config.health_token_increase--; l6.setText("Health Token Inc: " + config.health_token_increase); });
        plus6.setOnAction(e -> { config.health_token_increase++; l6.setText("Health Token Inc: " + config.health_token_increase); });
        root.add(new HBox(hBoxSpacing, l6, min6, plus6), 0, 5);

        Label l7 = new Label("Vacuum Token Inc: " + config.vacuum_token_increase);
        l7.setPrefWidth(labelWidth);
        l7.setStyle(labelStyle);
        Button min7 = new Button("-");
        Button plus7 = new Button("+");
        min7.setOnAction(e -> { config.vacuum_token_increase--; l7.setText("Vacuum Token Inc: " + config.vacuum_token_increase); });
        plus7.setOnAction(e -> { config.vacuum_token_increase++; l7.setText("Vacuum Token Inc: " + config.vacuum_token_increase); });
        root.add(new HBox(hBoxSpacing, l7, min7, plus7), 0, 6);

        Label l8 = new Label("Eye Token Dur: " + config.eye_token_duration);
        l8.setPrefWidth(labelWidth);
        l8.setStyle(labelStyle);
        Button min8 = new Button("-");
        Button plus8 = new Button("+");
        min8.setOnAction(e -> { config.eye_token_duration--; l8.setText("Eye Token Dur: " + config.eye_token_duration); });
        plus8.setOnAction(e -> { config.eye_token_duration++; l8.setText("Eye Token Dur: " + config.eye_token_duration); });
        root.add(new HBox(hBoxSpacing, l8, min8, plus8), 0, 7);

// column 1

        Label l9 = new Label("L1 Area X: " + config.level_1_playable_area_x);
        l9.setPrefWidth(labelWidth);
        l9.setStyle(labelStyle);
        Button min9 = new Button("-");
        Button plus9 = new Button("+");
        min9.setOnAction(e -> { config.level_1_playable_area_x -= 10; l9.setText("L1 Area X: " + config.level_1_playable_area_x); });
        plus9.setOnAction(e -> { config.level_1_playable_area_x += 10; l9.setText("L1 Area X: " + config.level_1_playable_area_x); });
        root.add(new HBox(hBoxSpacing, l9, min9, plus9), 1, 0);

        Label l10 = new Label("L1 Area Y: " + config.level_1_playable_area_y);
        l10.setPrefWidth(labelWidth);
        l10.setStyle(labelStyle);
        Button min10 = new Button("-");
        Button plus10 = new Button("+");
        min10.setOnAction(e -> { config.level_1_playable_area_y -= 10; l10.setText("L1 Area Y: " + config.level_1_playable_area_y); });
        plus10.setOnAction(e -> { config.level_1_playable_area_y += 10; l10.setText("L1 Area Y: " + config.level_1_playable_area_y); });
        root.add(new HBox(hBoxSpacing, l10, min10, plus10), 1, 1);

        Label l11 = new Label("L1 Area W: " + config.level_1_playable_area_width);
        l11.setPrefWidth(labelWidth);
        l11.setStyle(labelStyle);
        Button min11 = new Button("-");
        Button plus11 = new Button("+");
        min11.setOnAction(e -> { config.level_1_playable_area_width -= 10; l11.setText("L1 Area W: " + config.level_1_playable_area_width); });
        plus11.setOnAction(e -> { config.level_1_playable_area_width += 10; l11.setText("L1 Area W: " + config.level_1_playable_area_width); });
        root.add(new HBox(hBoxSpacing, l11, min11, plus11), 1, 2);

        Label l12 = new Label("L1 Area H: " + config.level_1_playable_area_height);
        l12.setPrefWidth(labelWidth);
        l12.setStyle(labelStyle);
        Button min12 = new Button("-");
        Button plus12 = new Button("+");
        min12.setOnAction(e -> { config.level_1_playable_area_height -= 10; l12.setText("L1 Area H: " + config.level_1_playable_area_height); });
        plus12.setOnAction(e -> { config.level_1_playable_area_height += 10; l12.setText("L1 Area H: " + config.level_1_playable_area_height); });
        root.add(new HBox(hBoxSpacing, l12, min12, plus12), 1, 3);

        Label l13 = new Label("L1 Time: " + config.level_1_time);
        l13.setPrefWidth(labelWidth);
        l13.setStyle(labelStyle);
        Button min13 = new Button("-");
        Button plus13 = new Button("+");
        min13.setOnAction(e -> { config.level_1_time--; l13.setText("L1 Time: " + config.level_1_time); });
        plus13.setOnAction(e -> { config.level_1_time++; l13.setText("L1 Time: " + config.level_1_time); });
        root.add(new HBox(hBoxSpacing, l13, min13, plus13), 1, 4);

        Label l14 = new Label("L1 Ghosts: " + config.level_1_ghosts);
        l14.setPrefWidth(labelWidth);
        l14.setStyle(labelStyle);
        Button min14 = new Button("-");
        Button plus14 = new Button("+");
        min14.setOnAction(e -> { config.level_1_ghosts--; l14.setText("L1 Ghosts: " + config.level_1_ghosts); });
        plus14.setOnAction(e -> { config.level_1_ghosts++; l14.setText("L1 Ghosts: " + config.level_1_ghosts); });
        root.add(new HBox(hBoxSpacing, l14, min14, plus14), 1, 5);

        Label l15 = new Label("L1 Rippers: " + config.level_1_rippers);
        l15.setPrefWidth(labelWidth);
        l15.setStyle(labelStyle);
        Button min15 = new Button("-");
        Button plus15 = new Button("+");
        min15.setOnAction(e -> { config.level_1_rippers--; l15.setText("L1 Rippers: " + config.level_1_rippers); });
        plus15.setOnAction(e -> { config.level_1_rippers++; l15.setText("L1 Rippers: " + config.level_1_rippers); });
        root.add(new HBox(hBoxSpacing, l15, min15, plus15), 1, 6);

        Label l16 = new Label("L1 Wisps: " + config.level_1_wisps);
        l16.setPrefWidth(labelWidth);
        l16.setStyle(labelStyle);
        Button min16 = new Button("-");
        Button plus16 = new Button("+");
        min16.setOnAction(e -> { config.level_1_wisps--; l16.setText("L1 Wisps: " + config.level_1_wisps); });
        plus16.setOnAction(e -> { config.level_1_wisps++; l16.setText("L1 Wisps: " + config.level_1_wisps); });
        root.add(new HBox(hBoxSpacing, l16, min16, plus16), 1, 7);

// column 2

        Label l17 = new Label("L2 Area X: " + config.level_2_playable_area_x);
        l17.setPrefWidth(labelWidth);
        l17.setStyle(labelStyle);
        Button min17 = new Button("-");
        Button plus17 = new Button("+");
        min17.setOnAction(e -> { config.level_2_playable_area_x -= 10; l17.setText("L2 Area X: " + config.level_2_playable_area_x); });
        plus17.setOnAction(e -> { config.level_2_playable_area_x += 10; l17.setText("L2 Area X: " + config.level_2_playable_area_x); });
        root.add(new HBox(hBoxSpacing, l17, min17, plus17), 2, 0);

        Label l18 = new Label("L2 Area Y: " + config.level_2_playable_area_y);
        l18.setPrefWidth(labelWidth);
        l18.setStyle(labelStyle);
        Button min18 = new Button("-");
        Button plus18 = new Button("+");
        min18.setOnAction(e -> { config.level_2_playable_area_y -= 10; l18.setText("L2 Area Y: " + config.level_2_playable_area_y); });
        plus18.setOnAction(e -> { config.level_2_playable_area_y += 10; l18.setText("L2 Area Y: " + config.level_2_playable_area_y); });
        root.add(new HBox(hBoxSpacing, l18, min18, plus18), 2, 1);

        Label l19 = new Label("L2 Area W: " + config.level_2_playable_area_width);
        l19.setPrefWidth(labelWidth);
        l19.setStyle(labelStyle);
        Button min19 = new Button("-");
        Button plus19 = new Button("+");
        min19.setOnAction(e -> { config.level_2_playable_area_width -= 10; l19.setText("L2 Area W: " + config.level_2_playable_area_width); });
        plus19.setOnAction(e -> { config.level_2_playable_area_width += 10; l19.setText("L2 Area W: " + config.level_2_playable_area_width); });
        root.add(new HBox(hBoxSpacing, l19, min19, plus19), 2, 2);

        Label l20 = new Label("L2 Area H: " + config.level_2_playable_area_height);
        l20.setPrefWidth(labelWidth);
        l20.setStyle(labelStyle);
        Button min20 = new Button("-");
        Button plus20 = new Button("+");
        min20.setOnAction(e -> { config.level_2_playable_area_height -= 10; l20.setText("L2 Area H: " + config.level_2_playable_area_height); });
        plus20.setOnAction(e -> { config.level_2_playable_area_height += 10; l20.setText("L2 Area H: " + config.level_2_playable_area_height); });
        root.add(new HBox(hBoxSpacing, l20, min20, plus20), 2, 3);

        Label l21 = new Label("L2 Time: " + config.level_2_time);
        l21.setPrefWidth(labelWidth);
        l21.setStyle(labelStyle);
        Button min21 = new Button("-");
        Button plus21 = new Button("+");
        min21.setOnAction(e -> { config.level_2_time--; l21.setText("L2 Time: " + config.level_2_time); });
        plus21.setOnAction(e -> { config.level_2_time++; l21.setText("L2 Time: " + config.level_2_time); });
        root.add(new HBox(hBoxSpacing, l21, min21, plus21), 2, 4);

        Label l22 = new Label("L2 Ghosts: " + config.level_2_ghosts);
        l22.setPrefWidth(labelWidth);
        l22.setStyle(labelStyle);
        Button min22 = new Button("-");
        Button plus22 = new Button("+");
        min22.setOnAction(e -> { config.level_2_ghosts--; l22.setText("L2 Ghosts: " + config.level_2_ghosts); });
        plus22.setOnAction(e -> { config.level_2_ghosts++; l22.setText("L2 Ghosts: " + config.level_2_ghosts); });
        root.add(new HBox(hBoxSpacing, l22, min22, plus22), 2, 5);

        Label l23 = new Label("L2 Rippers: " + config.level_2_rippers);
        l23.setPrefWidth(labelWidth);
        l23.setStyle(labelStyle);
        Button min23 = new Button("-");
        Button plus23 = new Button("+");
        min23.setOnAction(e -> { config.level_2_rippers--; l23.setText("L2 Rippers: " + config.level_2_rippers); });
        plus23.setOnAction(e -> { config.level_2_rippers++; l23.setText("L2 Rippers: " + config.level_2_rippers); });
        root.add(new HBox(hBoxSpacing, l23, min23, plus23), 2, 6);

        Label l24 = new Label("L2 Wisps: " + config.level_2_wisps);
        l24.setPrefWidth(labelWidth);
        l24.setStyle(labelStyle);
        Button min24 = new Button("-");
        Button plus24 = new Button("+");
        min24.setOnAction(e -> { config.level_2_wisps--; l24.setText("L2 Wisps: " + config.level_2_wisps); });
        plus24.setOnAction(e -> { config.level_2_wisps++; l24.setText("L2 Wisps: " + config.level_2_wisps); });
        root.add(new HBox(hBoxSpacing, l24, min24, plus24), 2, 7);

// column 3

        Label l25 = new Label("L3 Area X: " + config.level_3_playable_area_x);
        l25.setPrefWidth(labelWidth);
        l25.setStyle(labelStyle);
        Button min25 = new Button("-");
        Button plus25 = new Button("+");
        min25.setOnAction(e -> { config.level_3_playable_area_x -= 10; l25.setText("L3 Area X: " + config.level_3_playable_area_x); });
        plus25.setOnAction(e -> { config.level_3_playable_area_x += 10; l25.setText("L3 Area X: " + config.level_3_playable_area_x); });
        root.add(new HBox(hBoxSpacing, l25, min25, plus25), 3, 0);

        Label l26 = new Label("L3 Area Y: " + config.level_3_playable_area_y);
        l26.setPrefWidth(labelWidth);
        l26.setStyle(labelStyle);
        Button min26 = new Button("-");
        Button plus26 = new Button("+");
        min26.setOnAction(e -> { config.level_3_playable_area_y -= 10; l26.setText("L3 Area Y: " + config.level_3_playable_area_y); });
        plus26.setOnAction(e -> { config.level_3_playable_area_y += 10; l26.setText("L3 Area Y: " + config.level_3_playable_area_y); });
        root.add(new HBox(hBoxSpacing, l26, min26, plus26), 3, 1);

        Label l27 = new Label("L3 Area W: " + config.level_3_playable_area_width);
        l27.setPrefWidth(labelWidth);
        l27.setStyle(labelStyle);
        Button min27 = new Button("-");
        Button plus27 = new Button("+");
        min27.setOnAction(e -> { config.level_3_playable_area_width -= 10; l27.setText("L3 Area W: " + config.level_3_playable_area_width); });
        plus27.setOnAction(e -> { config.level_3_playable_area_width += 10; l27.setText("L3 Area W: " + config.level_3_playable_area_width); });
        root.add(new HBox(hBoxSpacing, l27, min27, plus27), 3, 2);

        Label l28 = new Label("L3 Area H: " + config.level_3_playable_area_height);
        l28.setPrefWidth(labelWidth);
        l28.setStyle(labelStyle);
        Button min28 = new Button("-");
        Button plus28 = new Button("+");
        min28.setOnAction(e -> { config.level_3_playable_area_height -= 10; l28.setText("L3 Area H: " + config.level_3_playable_area_height); });
        plus28.setOnAction(e -> { config.level_3_playable_area_height += 10; l28.setText("L3 Area H: " + config.level_3_playable_area_height); });
        root.add(new HBox(hBoxSpacing, l28, min28, plus28), 3, 3);

        Label l29 = new Label("L3 Time: " + config.level_3_time);
        l29.setPrefWidth(labelWidth);
        l29.setStyle(labelStyle);
        Button min29 = new Button("-");
        Button plus29 = new Button("+");
        min29.setOnAction(e -> { config.level_3_time--; l29.setText("L3 Time: " + config.level_3_time); });
        plus29.setOnAction(e -> { config.level_3_time++; l29.setText("L3 Time: " + config.level_3_time); });
        root.add(new HBox(hBoxSpacing, l29, min29, plus29), 3, 4);

        Label l30 = new Label("L3 Ghosts: " + config.level_3_ghosts);
        l30.setPrefWidth(labelWidth);
        l30.setStyle(labelStyle);
        Button min30 = new Button("-");
        Button plus30 = new Button("+");
        min30.setOnAction(e -> { config.level_3_ghosts--; l30.setText("L3 Ghosts: " + config.level_3_ghosts); });
        plus30.setOnAction(e -> { config.level_3_ghosts++; l30.setText("L3 Ghosts: " + config.level_3_ghosts); });
        root.add(new HBox(hBoxSpacing, l30, min30, plus30), 3, 5);

        Label l31 = new Label("L3 Rippers: " + config.level_3_rippers);
        l31.setPrefWidth(labelWidth);
        l31.setStyle(labelStyle);
        Button min31 = new Button("-");
        Button plus31 = new Button("+");
        min31.setOnAction(e -> { config.level_3_rippers--; l31.setText("L3 Rippers: " + config.level_3_rippers); });
        plus31.setOnAction(e -> { config.level_3_rippers++; l31.setText("L3 Rippers: " + config.level_3_rippers); });
        root.add(new HBox(hBoxSpacing, l31, min31, plus31), 3, 6);

        Label l32 = new Label("L3 Wisps: " + config.level_3_wisps);
        l32.setPrefWidth(labelWidth);
        l32.setStyle(labelStyle);
        Button min32 = new Button("-");
        Button plus32 = new Button("+");
        min32.setOnAction(e -> { config.level_3_wisps--; l32.setText("L3 Wisps: " + config.level_3_wisps); });
        plus32.setOnAction(e -> { config.level_3_wisps++; l32.setText("L3 Wisps: " + config.level_3_wisps); });
        root.add(new HBox(hBoxSpacing, l32, min32, plus32), 3, 7);
    }
}