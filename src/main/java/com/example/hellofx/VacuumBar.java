package com.example.hellofx;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

public class VacuumBar {
    private double posX;
    private double posY;
    private double barPercentage;
    private Rectangle rectangle;
    private Label healthLabel;

    public VacuumBar(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        this.barPercentage = 100;

        Rectangle rect = new Rectangle(50, 400, Color.RED);
        rect.setX(posX);
        rect.setY(posY);

        this.setRectangle(rect);
        this.setVacuumLabel(new Label("VACUUM: "));
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

    public Label getVacuumLabel() {
        return healthLabel;
    }

    public void setVacuumLabel(Label healthLabel) {
        this.healthLabel = healthLabel;
    }

    public void setBarPercentage(double barPercentage) {
        this.barPercentage = barPercentage;
        this.rectangle.setHeight(200*barPercentage);
    }
}
