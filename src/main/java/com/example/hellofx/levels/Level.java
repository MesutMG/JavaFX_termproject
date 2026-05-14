package com.example.hellofx.levels;

import com.example.hellofx.*;
import com.example.hellofx.entities.*;
import com.example.hellofx.tokens.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public abstract class Level extends Application {
    public ConfigReader config = new ConfigReader();
    public boolean isConfig = config.readConfig("src/main/java/com/example/hellofx/config.txt");
    protected int scoreText = 0;
    protected Label scoreLabel;
    protected Label timeRemainingLabel;
    protected long lastTimerUpdate = 0;
    protected int totalMinute;
    protected int totalSecond;
    protected static final int DEFAULT_WIDTH   = 1280;
    protected static final int DEFAULT_HEIGHT  = 720;
    protected static final int HEALTHBAR_POSX  = DEFAULT_WIDTH - 80;
    protected static final int HEALTHBAR_POSY  = 160;
    protected static final int VACUUMBAR_POSX  = 30;
    protected static final int VACUUMBAR_POSY  = 160;
    protected int playAreaX = getPlayAreaX();
    protected int playAreaY = getPlayAreaY();
    protected int playAreaW = getPlayAreaW();
    protected int playAreaH = getPlayAreaH();
    protected HealthBar hBar;
    protected VacuumBar vBar;
    protected Player player;
    protected ArrayList<Enemy> enemies = new ArrayList<>();
    protected Rectangle playableArea;
    protected boolean goUp, goDown, goLeft, goRight, vacuumState, rotateL, rotateR;
    protected boolean cheat = false;
    protected Pane gameRoot;
    protected ArrayList<Token> tokens = new ArrayList<>();
    protected long lastTokenSpawnTime;
    protected long eyeRevealEndTime = 0;

    // Abstract methods subclasses must implement

    public abstract String getLevelTitle();
    public abstract int getTimeLimitMinutes();
    public abstract int getTimeLimitSeconds();
    public abstract String getBackgroundImagePath();
    public abstract String[] getOverlayImagePaths();
    public abstract int getGhostCount();
    public abstract int getRipperCount();
    public abstract int getWispCount();
    public abstract Level createNextLevel();
    public abstract Level createRetryLevel();
    public abstract String getNextLevelTitle();
    public abstract int getPlayAreaX();
    public abstract int getPlayAreaY();
    public abstract int getPlayAreaW();
    public abstract int getPlayAreaH();

    // Utility for subclasses to generate random X inside the play area
    protected double randomX() {
        return (Math.random() * playAreaW) + playAreaX;
    }

    // Utility for subclasses to generate random Y inside the play area
    protected double randomY() {
        return (Math.random() * playAreaH) + playAreaY;
    }

    @Override
    public void start(Stage stage) {

        if(isConfig){ //if config file doesn't exist
            //Please upload config.txt file and restart game
            Platform.exit();
        }

        Scene scene = createScene(DEFAULT_WIDTH, DEFAULT_HEIGHT, getGhostCount(), getRipperCount(), getWispCount());
        stage.setTitle(getLevelTitle());
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setScene(scene);
        stage.show();
    }

    public Scene createScene(double width, double height, int ghosts, int rippers, int wisps) {
        totalMinute = getTimeLimitMinutes();
        totalSecond = getTimeLimitSeconds();

        scoreLabel = new Label("Score: " + scoreText);
        Pane root = new Pane();
        this.gameRoot = root;
        this.lastTokenSpawnTime = System.currentTimeMillis();

        Image bgImage = new Image(getBackgroundImagePath());
        BackgroundImage bg = new BackgroundImage(
                bgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(width, height, false, false, false, true)
        );

        player = new Player(width / 2, height / 2);

        spawnEnemies(ghosts, rippers, wisps);

        hBar = new HealthBar(HEALTHBAR_POSX, HEALTHBAR_POSY);
        vBar = new VacuumBar(VACUUMBAR_POSX, VACUUMBAR_POSY);

        playableArea = new Rectangle(playAreaX, playAreaY, playAreaW, playAreaH);
        playableArea.setFill(Color.TRANSPARENT);
        playableArea.setStroke(Color.TRANSPARENT);
        playableArea.setStrokeWidth(3);

        timeRemainingLabel = new Label(String.format("Time: %d:%02d", totalMinute, totalSecond));
        timeRemainingLabel.setFont(Font.font(24));
        timeRemainingLabel.setStyle("-fx-text-fill: white; ");
        scoreLabel.setFont(Font.font(24));
        scoreLabel.setStyle("-fx-text-fill: white; ");

        VBox hudTop = new VBox(15);
        hudTop.setLayoutX((DEFAULT_WIDTH >> 1) - 50);
        hudTop.setLayoutY(15);
        hudTop.getChildren().addAll(scoreLabel, timeRemainingLabel);

        root.setBackground(new Background(bg));
        root.getChildren().add(playableArea);

        for (Enemy e : enemies) {
            root.getChildren().add(e.getBody());
            //config.txt'den alinacak-----------------------------------------------------------------------------
        }
        root.getChildren().addAll(player.getGroup());

        String[] overlays = getOverlayImagePaths();
        for (String path : overlays) {
            ImageView iv = new ImageView(new Image(path, false));
            root.getChildren().add(iv);
        }

        root.getChildren().addAll(hudTop, hBar.getRectangle(), vBar.getRectangle());

        Scene scene = getScene(width, height, root);

        AnimationTimer timer = new GameTimer();
        timer.start();

        return scene;
    }

    private Scene getScene(double width, double height, Pane root) {
        Scene scene = new Scene(root, width, height);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W: goUp        = true; break;
                case S: goDown      = true; break;
                case A: goLeft      = true; break;
                case D: goRight     = true; break;
                case LEFT:  rotateL = true; break;
                case RIGHT: rotateR = true; break;
                case SPACE: vacuumState = true; break;
                case C: cheat = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case W: goUp        = false; break;
                case S: goDown      = false; break;
                case A: goLeft      = false; break;
                case D: goRight     = false; break;
                case LEFT:  rotateL = false; break;
                case RIGHT: rotateR = false; break;
                case SPACE: vacuumState = false; break;
                case C: cheat = false; break;
            }
        });
        return scene;
    }

    private class GameTimer extends AnimationTimer {
        @Override
        public void handle(long now) {
            scoreHandler();
            timeRemainingLabelHandler(now);
            handlePlayerMovement();
            handleVacuum();

            /*for (Enemy e : enemies) {
                e.update();
            }*/

            handleEnemyMovement();
            handleHealth();
            handleCheat();
            handleToken();

            if (hasWon()) {
                this.stop();
                Pane root = (Pane) player.getGroup().getScene().getRoot();

                Rectangle overlay = new Rectangle(DEFAULT_WIDTH >> 1, DEFAULT_HEIGHT >> 1, Color.BLACK);
                overlay.setX(DEFAULT_WIDTH >> 2);
                overlay.setY(DEFAULT_HEIGHT >> 2);
                overlay.setOpacity(0.5);

                Label winLabel = new Label("You Won!");
                winLabel.setFont(Font.font(72));
                winLabel.setTextFill(Color.GREEN);
                winLabel.setLayoutX((DEFAULT_WIDTH >> 1) - 150);
                winLabel.setLayoutY((DEFAULT_HEIGHT >> 1) - 50);

                Button nextLevelBtn = new Button("Next Level");
                nextLevelBtn.setPrefWidth(240);
                nextLevelBtn.setPrefHeight(60);
                nextLevelBtn.setLayoutX((DEFAULT_WIDTH >> 1) - 120);
                nextLevelBtn.setLayoutY((DEFAULT_HEIGHT >> 1) + 50);
                applyButtonStyle(nextLevelBtn, false);
                nextLevelBtn.setOnMouseEntered(e -> applyButtonStyle(nextLevelBtn, true));
                nextLevelBtn.setOnMouseExited(e -> applyButtonStyle(nextLevelBtn, false));
                nextLevelBtn.setOnAction(e -> {
                    Stage stage = (Stage) root.getScene().getWindow();
                    Level nextLevel = createNextLevel();
                    Scene nextScene = nextLevel.createScene(stage.getScene().getWidth(), stage.getScene().getHeight(), nextLevel.getGhostCount(), nextLevel.getRipperCount(), nextLevel.getWispCount());
                    stage.setTitle(getNextLevelTitle());
                    stage.setScene(nextScene);
                });

                root.getChildren().addAll(overlay, winLabel, nextLevelBtn);
            }

            if (hasLost()) {
                this.stop();
                Pane root = (Pane) player.getGroup().getScene().getRoot();

                Rectangle overlay = new Rectangle(DEFAULT_WIDTH >> 1, DEFAULT_HEIGHT >> 1, Color.BLACK);
                overlay.setX(DEFAULT_WIDTH >> 2);
                overlay.setY(DEFAULT_HEIGHT >> 2);
                overlay.setOpacity(0.5);

                Label lostLabel = new Label("Game Over\nFinal Score: " + player.getScore());
                lostLabel.setAlignment(Pos.CENTER);
                lostLabel.setFont(Font.font(72));
                lostLabel.setTextFill(Color.RED);
                lostLabel.setLayoutX((DEFAULT_WIDTH >> 1) - 180);
                lostLabel.setLayoutY((DEFAULT_HEIGHT >> 1) - 150);

                Button retryBtn = new Button("Try Again");
                retryBtn.setPrefWidth(240);
                retryBtn.setPrefHeight(60);
                retryBtn.setLayoutX((DEFAULT_WIDTH >> 1) - 120);
                retryBtn.setLayoutY((DEFAULT_HEIGHT >> 1) + 50);
                applyButtonStyle(retryBtn, false);
                retryBtn.setOnMouseEntered(e -> applyButtonStyle(retryBtn, true));
                retryBtn.setOnMouseExited(e -> applyButtonStyle(retryBtn, false));
                retryBtn.setOnAction(e -> {
                    Stage stage = (Stage) root.getScene().getWindow();
                    Level retry = createRetryLevel();
                    retry.start(stage);
                    Scene retryScene = retry.createScene(stage.getScene().getWidth(), stage.getScene().getHeight(), retry.getGhostCount(), retry.getRipperCount(), retry.getWispCount());
                    stage.setTitle(getLevelTitle());
                    stage.setScene(retryScene);
                });

                root.getChildren().addAll(overlay, lostLabel, retryBtn);
            }
        }
    }

    private void scoreHandler() {
        scoreLabel.setText("Score: " + scoreText);
    }

    private void timeRemainingLabelHandler(long now) {
        if (lastTimerUpdate == 0) {
            lastTimerUpdate = now;
            return;
        }

        if (now - lastTimerUpdate >= 1_000_000_000L) {
            lastTimerUpdate = now;

            if (totalSecond == 0) {
                if (totalMinute == 0) {
                    Platform.exit();
                } else {
                    totalMinute -= 1;
                    totalSecond = 59;
                }
            } else {
                totalSecond -= 1;
            }

            timeRemainingLabel.setText(String.format("Time: %d:%02d", totalMinute, totalSecond));
        }
    }

    protected void spawnEnemies(int n, int m, int k) {
        for (int i = 0; i < n; i++) {
            enemies.add(new Ghost(randomX(), randomY()));
            //config.txt'den alinacak -----------------------------------------------------------------------------
        }
        for (int i = 0; i < m; i++) {
            enemies.add(new Ripper(randomX(), randomY()));
            //config.txt'den alinacak -----------------------------------------------------------------------------
        }
        for (int i = 0; i < k; i++) {
            enemies.add(new Wisp(randomX(), randomY()));
            //config.txt'den alinacak -----------------------------------------------------------------------------
        }
    }

    private void handlePlayerMovement() {
        int moveX    = 0;
        int moveY    = 0;
        double angle = player.getAngle();

        if (goUp)   { moveY -= (int) player.getSpeed();}
        if (goDown) { moveY += (int) player.getSpeed();}
        if (goLeft) { moveX -= (int) player.getSpeed();}
        if (goRight){ moveX += (int) player.getSpeed();}
        if (rotateL){ angle -= 0.1;}
        if (rotateR){ angle += 0.1;}

        // Check if anything changed
        if (moveX != 0 || moveY != 0 || angle != 0) {
            double newX = player.getPosX() + moveX;
            double newY = player.getPosY() + moveY;

            if (newX < playAreaX + 20) newX = playAreaX + 20;
            if (newX > playAreaX + playAreaW - 20) newX = playAreaX + playAreaW - 20;
            if (newY < playAreaY + 20) newY = playAreaY + 20;
            if (newY > playAreaY + playAreaH - 20) newY = playAreaY + playAreaH - 20;

            player.updatePosition(newX, newY, angle);
        }
    }

    private void handleEnemyMovement() {
        for (Enemy e : enemies) {
            double newX = e.getPosX() + Math.cos(e.getAngle()) * e.getSpeed();
            double newY = e.getPosY() + Math.sin(e.getAngle()) * e.getSpeed();
            double newAngle = e.getAngle();

            if (newX < playAreaX + 20) {
                newX = playAreaX + 20;
                newAngle = Math.PI - newAngle;
            } else if (newX > playAreaX + playAreaW - 20) {
                newX = playAreaX + playAreaW - 20;
                newAngle = Math.PI - newAngle;
            }

            if (newY < playAreaY + 20) {
                newY = playAreaY + 20;
                newAngle = -newAngle;
            } else if (newY > playAreaY + playAreaH - 20) {
                newY = playAreaY + playAreaH - 20;
                newAngle = -newAngle;
            }

            e.updatePosition(newX, newY, newAngle);
        }
    }

    private void handleVacuum() {
        if (vacuumState) {
            if (player.getVacuumPerc() <= 0) {
                player.setVacuumPerc(0);
                vBar.setBarPercentage(0);
                player.getTriangle().setVisible(false);
                for (Enemy e : enemies) e.getBody().setVisible(false);
                return;
            }
            player.setVacuumPerc(player.getVacuumPerc() - 1);
            vBar.setBarPercentage(player.getVacuumPerc());
            player.getTriangle().setVisible(true);

            for (int i = enemies.size() - 1; i >= 0; i--) {
                Enemy e = enemies.get(i);
                boolean collision = player.getTriangle().localToScene(player.getTriangle().getBoundsInLocal()).intersects(e.getBody().localToScene(e.getBody().getBoundsInLocal()));
                e.getBody().setVisible(collision);
                if (collision) {
                    e.setHealth(e.getHealth() - player.getAttackDamage());
                    if (!e.isAlive()) {
                        player.setScore(player.getScore() + e.getScore());
                        scoreText = player.getScore();
                        scoreLabel.setText("Score: " + scoreText);
                        gameRoot.getChildren().remove(e.getBody());
                        enemies.remove(i);
                    }
                }
            }
        } else {
            player.setVacuumPerc(Math.min(100, player.getVacuumPerc() + 0.1)); //config.txt'den alinacak--------------
            vBar.setBarPercentage(player.getVacuumPerc());
            player.getTriangle().setVisible(false);
            for (Enemy e : enemies) {
                e.getBody().setVisible(false);
            }
        }
    }

    private void handleHealth() {
        for (Enemy e : enemies) {
            if (player.getCircle().localToScene(player.getCircle().getBoundsInLocal()).intersects(e.getBody().localToScene(e.getBody().getBoundsInLocal()))) {
                player.setHealth(player.getHealth() - e.getAttackDamage());

                if (player.getHealth() <= 0) {
                    player.setHealth(0);
                    player.setAlive(false);
                }
                hBar.setBarPercentage(player.getHealth());
            }
        }
    }

    private void handleCheat() {
        if (cheat) {
            playableArea.setStroke(Color.RED);
            for (Enemy e : enemies) e.getBody().setVisible(true);
        } else {
            playableArea.setStroke(Color.TRANSPARENT);
        }
    }

    private boolean hasWon() {
        return enemies.isEmpty();
    }

    private boolean hasLost() {
        return !(player.isAlive()) || (totalMinute == 0 && totalSecond == 0);
    }

    private void handleToken() {
        long currentTime = System.currentTimeMillis();

        // Spawn a new random token every 5 seconds
        if (currentTime - lastTokenSpawnTime >= 5000) {
            lastTokenSpawnTime = currentTime;
            double x = (Math.random() * (playAreaW - 40)) + playAreaX + 20;
            double y = (Math.random() * (playAreaH - 40)) + playAreaY + 20;

            int type = (int) (Math.random() * 3);
            Token token = switch (type) {
                case 0 -> new HealthToken(x, y);
                case 1 -> new RangeToken(x, y);
                default -> new EyeToken(x, y);
            };

            tokens.add(token);
            gameRoot.getChildren().add(token.getBody());
        }

        // Check collision with player and collect tokens
        for (int i = tokens.size() - 1; i >= 0; i--) {
            Token token = tokens.get(i);
            boolean collision = player.getCircle()
                    .localToScene(player.getCircle().getBoundsInLocal())
                    .intersects(token.getBody().localToScene(token.getBody().getBoundsInLocal()));

            if (collision) {
                token.tokenUsed(player);

                if (token instanceof HealthToken) {
                    hBar.setBarPercentage(player.getHealth());
                }
                if (token instanceof EyeToken) {
                    eyeRevealEndTime = currentTime + 5000;
                    //config.txt'den alinacak------------------------------------------------------------------------
                }

                gameRoot.getChildren().remove(token.getBody());
                tokens.remove(i);
            }
        }

        // Eye reveal effect: show all alive enemies for 5 seconds
        if (currentTime < eyeRevealEndTime) {
            for (Enemy e : enemies) {
                e.getBody().setVisible(true);
            }
        }
    }

    protected void applyButtonStyle(Button button, boolean hover) {
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
}
