package com.example.hellofx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import java.time.LocalTime;

public class LevelTwoScreen extends Application {
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

        HealthBar hBar = new HealthBar(HEALTHBAR_POSX, HEALTHBAR_POSY);
        VacuumBar vBar = new VacuumBar(VACUUMBAR_POSX, VACUUMBAR_POSY);

        scoreLabel.setFont(Font.font(24));

        timeRemainingLabel = new Label("Time: " + localDeviceMinute + "." + localDeviceSecond);
        timeRemainingLabel.setFont(Font.font(24));

        VBox hudTop = new VBox(15); //15 bosluk
        hudTop.setLayoutX((DEFAULT_WIDTH / 2) - 50);
        hudTop.setLayoutY(15);
        hudTop.getChildren().addAll(scoreLabel, timeRemainingLabel);

        root.getChildren().addAll(hudTop,hBar.getRectangle(), vBar.getRectangle());


        AnimationTimer timer = new MyTimer();
        timer.start();

        return new Scene(root, width, height);
    }

    private class MyTimer extends AnimationTimer {

        @Override
        public void handle(long now) {
            doHandle();
        }

        private void doHandle() {

            //Level 1
            timeRemainingLabelHandler();
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

}
