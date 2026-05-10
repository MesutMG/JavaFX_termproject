package com.example.hellofx.entities;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;

public class Ghost extends Enemy {
    private Circle  circle;
    private Rectangle rectangle;
    private final int CIRCLE_RADIUS = 15;

    public Ghost(double posX, double posY){
        super(posX, posY);
        this.maxHealth = 100;
        this.attackDamage = 0.1;
        this.health = this.maxHealth;

        Circle circle = new Circle(CIRCLE_RADIUS, Color.WHITE);
        circle.setCenterX(0);
        circle.setCenterY(0);
        circle.setOpacity(0.5);
        this.setCircle(circle);

        Rectangle rectangle = new Rectangle(30,15, Color.WHITE);
        rectangle.setY(0);
        rectangle.setX(-CIRCLE_RADIUS);
        rectangle.setOpacity(0.5);
        this.setRectangle(rectangle);
        
        this.group = new Group(this.rectangle, this.circle);
        this.group.setTranslateX(posX);
        this.group.setTranslateY(posY);
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



}
