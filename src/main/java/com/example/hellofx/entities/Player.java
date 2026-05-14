package com.example.hellofx.entities;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.scene.Group;

public class Player extends Entity {
    private double  vacuumPerc;
    private double  vacuumWidth = 30;
    private Circle  circle;
    private Polygon triangle;
    private final int CIRCLE_RADIUS = 20;

    public Player(double posX, double posY) {
        super(posX, posY);
        this.maxHealth  = 100;//config.txt'den alinacak---------------------------------------------------------------
        this.health     = 100;
        this.posX       = posX;
        this.posY       = posY;
        this.score      = 0;
        this.isAlive    = true;
        this.vacuumPerc = 100;//config.txt'den alinacak (bu 100 kalsin ama decreasing amountu degistirsin)-------------
        this.angle      = 0;
        this.speed      = 5;
        this.attackDamage = 1;

        Circle circle = new Circle(CIRCLE_RADIUS, Color.ORANGE);
        circle.setCenterX(0);
        circle.setCenterY(0);
        circle.setStroke(Color.BLACK);
        this.setCircle(circle);

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                0.0, 0.0,
                100.0, -vacuumWidth,
                100.0, vacuumWidth
        );
        triangle.setFill(Color.RED);
        triangle.setStroke(Color.WHITE);
        triangle.setOpacity(0.7);
        this.setTriangle(triangle);

        this.group = new Group(this.triangle, this.circle);
        this.group.setTranslateX(posX);
        this.group.setTranslateY(posY);
    }

    public Group getGroup() {
        return group;
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

    public Polygon getTriangle() {
        return triangle;
    }

    public void setTriangle(Polygon triangle) {
        this.triangle = triangle;
    }

    public double getVacuumPerc() {
        return vacuumPerc;
    }

    public double getVacuumWidth() { return vacuumWidth; }

    public void setVacuumWidth(double vacuumWidth) { this.vacuumWidth = vacuumWidth; }

    public void setVacuumPerc(double vacuumPerc) {
        if (vacuumPerc > 100) {
            this.vacuumPerc = 100;
        } else if (vacuumPerc < 0){
            this.vacuumPerc = 0;
        } else {
            this.vacuumPerc = vacuumPerc;
        }
    }

    public void updatePosition(double newX, double newY, double newAngle) {
        this.posX = newX;
        this.posY = newY;
        this.angle = newAngle;

        this.group.setTranslateX(newX);
        this.group.setTranslateY(newY);

        this.triangle.getPoints().setAll(
                0.0, 0.0,

                (100 * Math.cos(angle)) - (-vacuumWidth * Math.sin(angle)),
                (100 * Math.sin(angle)) + (-vacuumWidth * Math.cos(angle)),

                (100 * Math.cos(angle)) - (vacuumWidth * Math.sin(angle)),
                (100 * Math.sin(angle)) + (vacuumWidth * Math.cos(angle))
        );
    }

}
