package com.example.hellofx;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

public class VacuumBar {
    private double posX;
    private double posY;
    private double barPercentage;
    private Rectangle rectangle = new Rectangle(30, 200, Color.RED);
    private Label vacuumLabel = new Label("VACUUM: ");

    public VacuumBar (double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        this.barPercentage = 100;
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

    public void setBarPercentage(double barPercentage) {
        this.barPercentage = barPercentage;
        this.rectangle.setHeight(200*barPercentage);
    }
}
