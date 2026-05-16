package ghosthunterinc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

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
        root.setBackground(buildBackgroundImage());

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

    private Background buildBackgroundImage() {
        Image image = new Image(Objects.requireNonNull(getClass().getResource("/img/mainmenu.png")).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        return new Background(backgroundImage);
    }
}