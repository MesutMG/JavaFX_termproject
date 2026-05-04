package com.example.hellofx;

import javafx.application.Application;
import javafx.application.Platform;
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

public class TitleScreen extends Application {
	private static final double DEFAULT_WIDTH = 1280;
	private static final double DEFAULT_HEIGHT = 720;
	private final Button startGameButton = new Button("Start Game");
	private final Button selectLevelButton = new Button("Select Level");
	private final Button exitButton = new Button("Exit");

	@Override
	public void start(Stage stage) {
		Scene scene = createScene(DEFAULT_WIDTH, DEFAULT_HEIGHT, stage);
		stage.setTitle("Title Screen");
		stage.setScene(scene);
		stage.show();
	}

	public Scene createScene(double width, double height, Stage stage) {
		StackPane root = new StackPane();
		root.setPadding(new Insets(24));
		root.setBackground(buildBackgroundImage());

		VBox menu = new VBox(18);
		menu.setAlignment(Pos.CENTER);

		startGameButton.setPrefWidth(200);
		selectLevelButton.setPrefWidth(200);
		exitButton.setPrefWidth(200);
		startGameButton.setPrefHeight(64);
		selectLevelButton.setPrefHeight(64);
		exitButton.setPrefHeight(64);

		styleMenuButton(startGameButton);
		styleMenuButton(selectLevelButton);
		styleMenuButton(exitButton);

		startGameButton.setOnAction(event -> switchToGame(stage));
		selectLevelButton.setOnAction(event -> switchToSelectLevel(stage));
		exitButton.setOnAction(event -> Platform.exit());

		menu.getChildren().addAll(startGameButton, selectLevelButton, exitButton);
		root.getChildren().add(menu);

		return new Scene(root, width, height);
	}

	private void switchToGame(Stage stage) {
		HelloApplication game = new HelloApplication();
		double width = stage.getWidth();
		double height = stage.getHeight();
		Scene gameScene = game.createScene(width, height);
		stage.setTitle("AnimationTimer");
		stage.setScene(gameScene);
	}

	private void switchToSelectLevel(Stage stage) {
		SelectLevel selectLevel = new SelectLevel();
		double width = stage.getScene().getWidth();
		double height = stage.getScene().getHeight();
		Scene levelScene = selectLevel.createScene(stage, width, height);
		stage.setTitle("Select Level");
		stage.setScene(levelScene);
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
				"-fx-font-size: 26px;" +
				"-fx-font-weight: 800;" +
				"-fx-letter-spacing: 2px;" +
				"-fx-background-radius: 6;" +
				"-fx-border-radius: 6;"
		);
	}

	private Background buildBackgroundImage() {
		var imageUrl = getClass().getResource("./images/background.png");
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

	public Button getStartGameButton() {
		return startGameButton;
	}

	public Button getSelectLevelButton() {
		return selectLevelButton;
	}

	public Button getExitButton() {
		return exitButton;
	}
}
