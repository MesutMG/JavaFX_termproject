package com.example.hellofx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import java.time.LocalTime;

public class LevelOneScreen extends Application {
    private int scoreText = 0;
    private double opacity = 1;
    private Label timeRemainingLabel;
    int oldMinute = LocalTime.now().getMinute();
    int oldSecond = LocalTime.now().getSecond();
    int localDeviceMinute;
    int localDeviceSecond;
    private int totalMinute = 3;
    private int totalSecond = 0;
    private static final int    DEFAULT_WIDTH   = 1280;
    private static final int    DEFAULT_HEIGHT  = 720;
    private static final int    HEALTHBAR_POSX  = DEFAULT_WIDTH - 80;
    private static final int    HEALTHBAR_POSY  = 160; //defaultheight/2 - 200
    private static final int    VACUUMBAR_POSX  = 30;
    private static final int    VACUUMBAR_POSY  = 160; //defaultheight/2 - 200
    private static final int    PLAY_AREA_X     = 240;
    private static final int    PLAY_AREA_Y     = 60;
    private static final int    PLAY_AREA_W     = 800;
    private static final int    PLAY_AREA_H     = 600;
    private HealthBar hBar;
    private VacuumBar vBar;
    private Character player;
    private Enemy1 enemy1;
    private Enemy2 enemy2;
    private Enemy3 enemy3;
    private boolean goUp, goDown, goLeft, goRight, vacuumState, rotateL, rotateR;
    private final int PLAYER_SPEED = 5;
    @Override
    public void start(Stage stage) {
        Scene scene = createScene(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        stage.setTitle("Level 1");
        stage.setScene(scene);
        stage.show();
    }

    public Scene createScene(double width, double height) {
        Label       scoreLabel = new Label("Score: " + scoreText);
        Pane        root       = new Pane();
        Image       bg = new Image("file:img/bg.jpg");

        BackgroundImage bgImage = new BackgroundImage(
                bg,
                BackgroundRepeat.NO_REPEAT, //otherwise tiling yapiyor
                BackgroundRepeat.NO_REPEAT, //otherwise tiling yapiyor
                BackgroundPosition.CENTER,
                new BackgroundSize(width, height, false, false, false, true)
                //widthasPercentage, heigthaspercentage, cropping engelleme, scale yardimi
        );

        player= new Character(width/2, height/2);
        enemy1= new Enemy1(400,200); // FOR TESTING PURPOSES ------------------------------------------
        enemy2= new Enemy2(300,300); // FOR TESTING PURPOSES ------------------------------------------
        enemy3= new Enemy3(500,500); // same
        hBar  = new HealthBar(HEALTHBAR_POSX, HEALTHBAR_POSY);
        vBar  = new VacuumBar(VACUUMBAR_POSX, VACUUMBAR_POSY);

        Rectangle playableArea = new Rectangle(PLAY_AREA_X, PLAY_AREA_Y, PLAY_AREA_W, PLAY_AREA_H);
        playableArea.setFill(Color.TRANSPARENT);
        playableArea.setStroke(Color.WHITE);
        playableArea.setStrokeWidth(3);

        timeRemainingLabel = new Label("Time: " + localDeviceMinute + "." + localDeviceSecond);
        timeRemainingLabel.setFont(Font.font(24));
        scoreLabel.setFont(Font.font(24));

        VBox hudTop = new VBox(15);
        hudTop.setLayoutX((DEFAULT_WIDTH / 2) - 50);
        hudTop.setLayoutY(15);
        hudTop.getChildren().addAll(scoreLabel, timeRemainingLabel);

        root.setBackground(new Background(bgImage));
        root.getChildren().add(playableArea);
        root.getChildren().addAll(enemy1.draw());
        root.getChildren().addAll(enemy2.draw());
        root.getChildren().addAll(enemy3.draw());
        root.getChildren().addAll(player.getTriangle(), player.getCircle());
        root.getChildren().addAll(hudTop,hBar.getRectangle(), vBar.getRectangle());

        Scene scene = new Scene(root, width, height);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W: goUp        = true; break;
                case S: goDown      = true; break;
                case A: goLeft      = true; break;
                case D: goRight     = true; break;
                case LEFT:  rotateL = true; break;
                case RIGHT: rotateR = true; break;
                case V: vacuumState = true; break;
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
                case V: vacuumState = false; break;
            }
        });

        AnimationTimer timer = new MyTimer();
        timer.start();

        return scene;
    }

    private class MyTimer extends AnimationTimer {

        @Override
        public void handle(long now) {
            doHandle();
        }

        private void doHandle() {

            //Level 1
            timeRemainingLabelHandler();

            handlePlayerMovement();

            handleVacuum();

            handleHealth();

            enemy3.spinRays();
        }


    }

    private void timeRemainingLabelHandler() {
        localDeviceMinute = LocalTime.now().getMinute();
        localDeviceSecond = LocalTime.now().getSecond();
        if (localDeviceSecond != oldSecond) {
            if (totalSecond == 0) {
                if (totalMinute == 0) {
                    System.exit(1);
                } else {
                    totalMinute -= 1;
                    totalSecond = 59;
                }

            } else {
                totalSecond -= 1;
            }
            timeRemainingLabel.setText("Time: " + Integer.toString(totalMinute) + "." + Integer.toString(totalSecond));
        }

        oldMinute = localDeviceMinute;
        oldSecond = localDeviceSecond;
    }

    private void handlePlayerMovement() {
        int moveX    = 0;
        int moveY    = 0;
        double angle = player.getRotAngle();

        if (goUp)   { moveY -= PLAYER_SPEED;}
        if (goDown) { moveY += PLAYER_SPEED;}
        if (goLeft) { moveX -= PLAYER_SPEED;}
        if (goRight){ moveX += PLAYER_SPEED;}
        if (rotateL){ angle -= 0.1;}
        if (rotateR){ angle += 0.1;}

        if (moveX != 0 || moveY != 0 || angle != 0) {

            double newX = player.getPosX() + moveX;
            double newY = player.getPosY() + moveY;

            if (newX < PLAY_AREA_X + 20) newX = PLAY_AREA_X + 20;
            if (newX > PLAY_AREA_X + PLAY_AREA_W - 20) newX = PLAY_AREA_X + PLAY_AREA_W - 20;
            if (newY < PLAY_AREA_Y + 20) newY = PLAY_AREA_Y + 20;
            if (newY > PLAY_AREA_Y + PLAY_AREA_H - 20) newY = PLAY_AREA_Y + PLAY_AREA_H - 20;

            player.updatePosition(newX, newY, angle);

            /*player.setPosX(player.getPosX() + moveX);
            player.setPosY(player.getPosY() + moveY);

            player.getTriangle().getPoints().setAll(
                    player.getPosX(), player.getPosY(),
                    player.getPosX() + 75, player.getPosY() - 30,
                    player.getPosX() + 75, player.getPosY() + 30
            );

            player.getCircle().setCenterX(player.getPosX());
            player.getCircle().setCenterY(player.getPosY());*/
        }
    }

    private void handleVacuum() {
        if (vacuumState) {
            player.setVacuumPerc(player.getVacuumPerc() - 0.5);
            vBar.setBarPercentage(player.getVacuumPerc());
            player.getTriangle().setVisible(true);
            
            boolean e1Collision = player.getTriangle().getBoundsInParent().intersects(enemy1.getCircle().getBoundsInParent());
            for (Node n : enemy1.draw()) n.setVisible(e1Collision);

            boolean e2Collision = player.getTriangle().getBoundsInParent().intersects(enemy2.getCircleOuter().getBoundsInParent());
            for (Node n : enemy2.draw()) n.setVisible(e2Collision);

            boolean e3Collision = player.getTriangle().getBoundsInParent().intersects(enemy3.getCircleFace().getBoundsInParent());
            for (Node n : enemy3.draw()) n.setVisible(e3Collision);
        } else {
            player.getTriangle().setVisible(false);
            for (Node n : enemy1.draw()) n.setVisible(false);
            for (Node n : enemy2.draw()) n.setVisible(false);
            for (Node n : enemy3.draw()) n.setVisible(false);
        }
    }

    private void handleHealth() {
        if (player.getCircle().getBoundsInParent().intersects(enemy1.getCircle().getBoundsInParent())) {
                player.setHealth(player.getHealth() - 0.1);

                if (player.getHealth() < 0) {
                    player.setHealth(0);
                }
                hBar.setBarPercentage(player.getHealth());
        }
    }
}

