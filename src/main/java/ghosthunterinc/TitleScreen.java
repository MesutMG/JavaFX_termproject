package fxproject;

import fxproject.levels.LevelOneScreen;
import fxproject.levels.LevelThreeScreen;
import fxproject.levels.LevelTwoScreen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.example.hellofx.levels.*;

public class TitleScreen extends Application {
	private LogReader log = new LogReader();
	private boolean isLog = log.readLog("src/main/java/com/example/hellofx/log.txt");
	private static final double DEFAULT_WIDTH = 1280;
	private static final double DEFAULT_HEIGHT = 720;
	private final Button loadGameButton = new Button("Load Game");
	private final Button startGameButton = new Button("Start Game");
	private final Button selectLevelButton = new Button("Select Level");
	private final Button editConfigButton = new Button("Edit Config");
	private final Button exitButton = new Button("Exit");

	@Override
	public void start(Stage stage) {
		Scene scene = createScene(DEFAULT_WIDTH, DEFAULT_HEIGHT, stage);
		stage.setTitle("Title Screen");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.setResizable(false);
		stage.show();
	}

	public Scene createScene(double width, double height, Stage stage) {

		if(!isLog){//if log file doesn't exist
			//Generate log.txt with default values 0,1
		}

		StackPane root = new StackPane();
		root.setPadding(new Insets(24));
		root.setBackground(buildBackgroundImage());

		VBox menu = new VBox(18);
		menu.setAlignment(Pos.CENTER);

		loadGameButton.setPrefWidth(200);
		startGameButton.setPrefWidth(200);
		selectLevelButton.setPrefWidth(200);
		editConfigButton.setPrefWidth(200);
		exitButton.setPrefWidth(200);

		loadGameButton.setPrefHeight(64);
		startGameButton.setPrefHeight(64);
		selectLevelButton.setPrefHeight(64);
		editConfigButton.setPrefHeight(64);
		exitButton.setPrefHeight(64);

		styleMenuButton(loadGameButton);
		styleMenuButton(startGameButton);
		styleMenuButton(selectLevelButton);
		styleMenuButton(editConfigButton);
		styleMenuButton(exitButton);

		loadGameButton.setOnAction(event -> loadLastLevel(stage));
		startGameButton.setOnAction(event -> switchToGame(stage));
		selectLevelButton.setOnAction(event -> switchToSelectLevel(stage));
		editConfigButton.setOnAction(event -> switchToEditConfig(stage));
		exitButton.setOnAction(event -> Platform.exit());

		menu.getChildren().addAll(loadGameButton, startGameButton, selectLevelButton, editConfigButton, exitButton);
		root.getChildren().add(menu);

		return new Scene(root, width, height);
	}

	private void switchToGame(Stage stage) {
		LevelOneScreen levelOneScreen = new LevelOneScreen();
		levelOneScreen.start(stage);
	}

	private void switchToSelectLevel(Stage stage) {
		SelectLevel selectLevel = new SelectLevel();
		Scene levelScene = selectLevel.createScene(stage, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		stage.setTitle("Select Level");
		stage.setScene(levelScene);
	}

	public void loadLastLevel(Stage stage){
		if(log.last_level <= 1){
			LevelOneScreen levelOneScreen = new LevelOneScreen();
			levelOneScreen.start(stage);
		} else if (log.last_level == 2) {
			LevelTwoScreen levelTwoScreen = new LevelTwoScreen();
			levelTwoScreen.start(stage);
		} else {
			LevelThreeScreen levelThreeScreen = new LevelThreeScreen();
			levelThreeScreen.start(stage);
		}
	}

	public void switchToEditConfig(Stage stage){
		EditConfig editConfig = new EditConfig();
		Scene editConfigScene = editConfig.createScene(stage, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		stage.setTitle("Edit Config");
		stage.setScene(editConfigScene);
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
		Image image = new Image("file:img/mainmenu.png");
		BackgroundImage backgroundImage = new BackgroundImage(
				image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				new BackgroundSize(100, 100, true, true, true, true)
		);
		return new Background(backgroundImage);
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
