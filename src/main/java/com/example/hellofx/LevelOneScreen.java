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

import java.time.LocalTime;

public class LevelOneScreen extends Application {
    private int scoreText = 0;
    private double opacity = 1;
    private Label timeRemainingLabel;
    int oldMinute = LocalTime.now().getMinute();
    int oldSecond = LocalTime.now().getSecond();
    private int totalMinute = 3;
    private int totalSecond = 0;
    private static final double DEFAULT_WIDTH = 1280;
    private static final double DEFAULT_HEIGHT = 720;
    private static final int HEALTHBAR_POSX = 1100;
    private static final int HEALTHBAR_POSY = 50;

    @Override
    public void start(Stage stage) {
        Scene scene = createScene(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        stage.setTitle("Level 1");
        stage.setScene(scene);
        stage.show();
    }

    public Scene createScene(double width, double height) {
        var root = new StackPane();

        HealthBar hBar = new HealthBar(HEALTHBAR_POSX, HEALTHBAR_POSY);

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

        int localDeviceMinute = LocalTime.now().getMinute();
        int localDeviceSecond = LocalTime.now().getSecond();

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
