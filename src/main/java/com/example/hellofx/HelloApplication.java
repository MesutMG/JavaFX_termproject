package com.example.hellofx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;

import java.time.LocalTime;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private int scoreText = 0;
    private double opacity = 1;
    private Label timeRemainingLabel;
    int oldMinute = LocalTime.now().getMinute();
    int oldSecond = LocalTime.now().getSecond();
    private int totalMinute = 3;
    private int totalSecond = 0;
    private int localDeviceMinute;
    private int localDeviceSecond;
    final int width = 800;
    final int height = 600;

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = createScene(500, 500);
        stage.setTitle("AnimationTimer");
        stage.setScene(scene);
        stage.show();
    }

    public Scene createScene(double width, double height) {
        Label scoreLabel;
        var root = new StackPane();

        scoreLabel = new Label("Score: " + scoreText);
        scoreLabel.setFont(Font.font(24));

        timeRemainingLabel = new Label("Time: " + localDeviceMinute + "." + localDeviceSecond);
        timeRemainingLabel.setFont(Font.font(24));

        VBox hudTop = new VBox(15); //15 boslug
        hudTop.setAlignment(Pos.TOP_CENTER);
        hudTop.getChildren().addAll(scoreLabel, timeRemainingLabel);

        root.getChildren().add(hudTop);

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

            timeRemainingLabelHandler();

            //scoreText++;
            //timeRemainingLabel.opacityProperty().set(opacity);

            if (opacity <= 0) {

                stop();
                System.out.println("Animation stopped");
            }


        }
    }

    private void timeRemainingLabelHandler(){

        localDeviceMinute = LocalTime.now().getMinute();
        localDeviceSecond = LocalTime.now().getSecond();

        if (localDeviceSecond != oldSecond) {
            if(totalSecond == 0){
                if(totalMinute == 0){
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

