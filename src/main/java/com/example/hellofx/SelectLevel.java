package com.example.hellofx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class SelectLevel extends Application {
    private static final double DEFAULT_WIDTH = 1280;
    private static final double DEFAULT_HEIGHT = 720;
    private final Button levelOneButton = new Button("Level 1");
    private final Button levelTwoButton = new Button("Level 2");
    private final Button levelThreeButton = new Button("Level 3");
    private final Button backButton = new Button("Back");

    @Override
    public void start(Stage stage) {
        Scene scene = createScene(stage, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        stage.setTitle("Select Level");
        stage.setScene(scene);
        stage.show();
    }

    public Scene createScene(Stage stage, double width, double height) {
        StackPane root = new StackPane();
        root.setPadding(new Insets(24));
        root.setBackground(buildBackgroundImage());

        VBox menu = new VBox(18);
        menu.setAlignment(Pos.CENTER);

        levelOneButton.setPrefWidth(240);
        levelTwoButton.setPrefWidth(240);
        levelThreeButton.setPrefWidth(240);
        backButton.setPrefWidth(240);
        levelOneButton.setPrefHeight(60);
        levelTwoButton.setPrefHeight(60);
        levelThreeButton.setPrefHeight(60);
        backButton.setPrefHeight(60);

        styleMenuButton(levelOneButton);
        styleMenuButton(levelTwoButton);
        styleMenuButton(levelThreeButton);
        styleMenuButton(backButton);

        backButton.setOnAction(event -> {
            TitleScreen titleScreen = new TitleScreen();
            double sceneWidth = stage.getScene().getWidth();
            double sceneHeight = stage.getScene().getHeight();
            Scene titleScene = titleScreen.createScene(sceneWidth, sceneHeight, stage);
            stage.setTitle("Title Screen");
            stage.setScene(titleScene);
        });

        menu.getChildren().addAll(levelOneButton, levelTwoButton, levelThreeButton, backButton);
        root.getChildren().add(menu);

        return new Scene(root, width, height);
    }

    private void styleMenuButton(Button button) {
        applyButtonStyle(button, false);
        button.setOnMouseEntered(event -> applyButtonStyle(button, true));
        button.setOnMouseExited(event -> applyButtonStyle(button, false));
    }

    private void applyButtonStyle(Button button, boolean hover) {
        String backgroundColor = hover ? "#b416e8" : "#8e10bf";
        String borderColor = hover ? "#5e0b8a" : "#4f007a";
        button.setStyle(
                "-fx-background-color: " + backgroundColor + ";" +
                "-fx-border-color: " + borderColor + ";" +
                "-fx-border-width: 3;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: 800;" +
                "-fx-letter-spacing: 2px;" +
                "-fx-background-radius: 6;" +
                "-fx-border-radius: 6;"
        );
    }

    private Background buildBackgroundImage() {
        var imageUrl = getClass().getResource("/com/example/hellofx/images/background.png");
        if (imageUrl != null) {
            Image image = new Image(imageUrl.toExternalForm());
            BackgroundImage backgroundImage = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, true)
            );
            return new Background(backgroundImage);
        }

        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#3a1f6b")),
                new Stop(1, Color.web("#2a124f"))
        );

        return new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY));
    }
}
