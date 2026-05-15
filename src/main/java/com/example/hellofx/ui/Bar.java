package com.example.hellofx.ui;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Bar{
    private double posX;
    private double posY;
    private double barPercentage;
    private Rectangle rectangleBar;
    private Rectangle rectangleBackground;
    private Group group;
    private Label barLabel;
    private final double BAR_HEIGHT = 400;
    private final double BAR_WIDTH  = 50;

    public Bar(double posX, double posY, String labelText) {
        this.posX = posX;
        this.posY = posY;
        this.barPercentage = 100;

        Rectangle rect = new Rectangle(BAR_WIDTH, BAR_HEIGHT, Color.RED);
        rect.setX(0);
        rect.setY(0);

        this.setRectangleBar(rect);
        Label textLabel =  new Label(labelText);
        textLabel.setStyle(
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: 800;" +
                        "-fx-letter-spacing: 1px;"
        );
        textLabel.setTranslateX(-13);
        textLabel.setTranslateY(-30);
        this.barLabel = textLabel;

        Rectangle rect2 = new Rectangle(BAR_WIDTH + 10, BAR_HEIGHT + 10, Color.BLACK);
        rect2.setX(- 5);
        rect2.setY(- 5);

        this.setRectangleBackground(rect2);

        this.group = new Group(this.rectangleBackground, this.rectangleBar, this.barLabel);
        this.group.setTranslateX(posX);
        this.group.setTranslateY(posY);
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }
    public double getPosX() {
        return posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
    public double getPosY() {
        return posY;
    }

    public void setRectangleBar(Rectangle rectangleBar) {
        this.rectangleBar = rectangleBar;
    }
    public Rectangle getRectangleBar() {
        return rectangleBar;
    }

    public void setRectangleBackground(Rectangle rectangle) {
        this.rectangleBackground = rectangle;
    }
    public Rectangle getRectangleBackground() {
        return rectangleBackground;
    }

    public void setBarLabel(Label barLabel) {
        this.barLabel = barLabel;
    }
    public Label getBarLabel() {
        return barLabel;
    }

    public void setBarPercentage(double barPercentage) {
        if((barPercentage <= 100) && (barPercentage >= 0)){
            this.barPercentage = barPercentage;

            double newHeight = BAR_HEIGHT * (barPercentage / 100.0);
            this.rectangleBar.setHeight(newHeight);

            double emptySpace = BAR_HEIGHT - newHeight;
            this.rectangleBar.setY(emptySpace);
        }

        else if (barPercentage > 100) {
            this.barPercentage = 100;
            this.rectangleBar.setHeight(BAR_HEIGHT);
            this.rectangleBar.setY(0);
        }

        else if (barPercentage < 0){
            this.barPercentage = 0;
            this.rectangleBar.setHeight(0);
            this.rectangleBar.setY(BAR_HEIGHT);
        }
    }
    public double getBarPercentage() {
        return barPercentage;
    }

    public Group getGroup() { return group; }

}
