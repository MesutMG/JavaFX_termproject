package com.example.hellofx;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

public class VacuumBar {
    private double posX;
    private double posY;
    private double barPercentage;
    private Rectangle rectangle;
    private Rectangle rectangleBackground;
    private Label healthLabel;
    private final double BAR_HEIGHT = 400;
    private final double BAR_WIDTH  = 50;

    public VacuumBar(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        this.barPercentage = 100;

        Rectangle rect = new Rectangle(BAR_WIDTH, BAR_HEIGHT, Color.RED);
        rect.setX(posX);
        rect.setY(posY);

        this.setRectangle(rect);
        this.setVacuumLabel(new Label("VACUUM: "));

        Rectangle rect2 = new Rectangle(BAR_WIDTH + 5, BAR_HEIGHT + 5, Color.BLACK);
        rect.setX(posX - 2.5);
        rect.setY(posY - 5);

        this.setRectangleBackground(rect2);

    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getBarPercentage() {
        return barPercentage;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setRectangleBackground(Rectangle rectangle) {
        this.rectangleBackground = rectangle;
    }

    public Rectangle getRectangleBackground() {
        return rectangleBackground;
    }

    public Label getVacuumLabel() {
        return healthLabel;
    }

    public void setVacuumLabel(Label healthLabel) {
        this.healthLabel = healthLabel;
    }

    public void setBarPercentage(double barPercentage) {
        this.barPercentage = barPercentage;

        double newHeight = BAR_HEIGHT * (barPercentage / 100.0);
        this.rectangle.setHeight(newHeight);

        double emptySpace = BAR_HEIGHT - newHeight;
        this.rectangle.setY(this.posY + emptySpace);
    }
}
