package com.example.hellofx;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Character {
    private double  health;
    private double  posX;
    private double  posY;
    private int     score;
    private double  vacuumPerc;
    private boolean isAlive;
    private Circle  circle;
    private final int CIRCLE_RADIUS = 20;

    Character(double posX, double posY){
        this.health     = 100;
        this.posX       = posX;
        this.posY       = posY;
        this.score      = 0;
        this.isAlive    = true;
        this.vacuumPerc = 100;

        Circle circle = new Circle(CIRCLE_RADIUS, Color.ORANGE);
        circle.setCenterX(posX);
        circle.setCenterY(posY);

        this.setCircle(circle);
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public double getVacuumPerc() {
        return vacuumPerc;
    }

    public void setVacuumPerc(double vacuumPerc) {
        this.vacuumPerc = vacuumPerc;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
