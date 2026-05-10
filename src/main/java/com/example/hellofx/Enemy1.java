package com.example.hellofx;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy1 {
    private double  health;
    private double  posX;
    private double  posY;
    private boolean isAlive;
    private Circle  circle;
    private Rectangle rectangle;
    private double opacity;
    private final int CIRCLE_RADIUS = 15;

    Enemy1(double posX, double posY){
        this.health     = 100;
        this.posX       = posX;
        this.posY       = posY;
        this.isAlive    = true;

        Circle circle = new Circle(CIRCLE_RADIUS, Color.WHITE);
        circle.setCenterX(posX);
        circle.setCenterY(posY);
        circle.setOpacity(0.5);
        this.setCircle(circle);

        Rectangle rectangle = new Rectangle(30,15, Color.WHITE);
        rectangle.setY(posY);
        rectangle.setX(posX - CIRCLE_RADIUS);
        rectangle.setOpacity(0.5);
        this.setRectangle(rectangle);
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

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.rectangle.setOpacity(opacity);
        this.circle.setOpacity(opacity);
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Node[] draw() {
        return new Node[]{this.rectangle, this.circle};
    }

}
