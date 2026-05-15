package com.example.hellofx.entities;
import com.example.hellofx.ConfigReader;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.Group;

public class Player extends Entity {
    private double  vacuumPerc;
    private double vacuum;
    private final double vacuumDecr;
    private final double vacuumIncr;
    private double  maxVacuum;
    private double  vacuumWidth = 30;
    private double vacuumHeight = 100;
    private double  vacuumPower = 1;
    private Circle  circle;
    private Polygon triangle;
    private final int CIRCLE_RADIUS = 20;

    public Player(double posX, double posY, ConfigReader config) {
        super(posX, posY);
        this.maxHealth  = config.maximum_health;
        this.health     = config.maximum_health;
        this.posX       = posX;
        this.posY       = posY;
        this.score      = 0;
        this.isAlive    = true;
        this.vacuumPerc = 100;
        this.maxVacuum  = config.maximum_vacuum;
        this.vacuum = this.maxVacuum;
        this.vacuumDecr = config.vacuum_decrease;
        this.vacuumIncr = config.vacuum_increase;
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
                vacuumHeight, -vacuumWidth,
                vacuumHeight, vacuumWidth
        );
        triangle.setFill(Color.RED);
        triangle.setStroke(Color.WHITE);
        triangle.setOpacity(0);
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

    public double getVacuum() {
        return vacuum;
    }

    public void setVacuum(double vacuum) {
        this.vacuum = vacuum;
    }

    public double getMaxVacuum() {
        return maxVacuum;
    }

    public void setMaxVacuum(double maxVacuum) {
        this.maxVacuum = maxVacuum;
    }

    public double getVacuumPower() {
        return vacuumPower;
    }

    public void setVacuumPower(double vacuumPower) {
        this.vacuumPower = vacuumPower;
    }

    public double getVacuumDecrease(){
        return this.vacuumDecr;
    }

    public double getVacuumIncrease() { return this.vacuumIncr;}

    public double getVacuumWidth() { return vacuumWidth; }

    public void setVacuumWidth(double vacuumWidth) { this.vacuumWidth = vacuumWidth; }

    public double getVacuumHeight() { return vacuumHeight; }

    public void setVacuumHeight(double vacuumHeight) { this.vacuumHeight = vacuumHeight; }

    public void setVacuumPerc(double vacuumPerc) {
        if (vacuumPerc > 100) {
            this.vacuumPerc = 100;
        } else if (vacuumPerc < 0){
            this.vacuumPerc = 0;
        } else {
            this.vacuumPerc = vacuumPerc;
        }
    }

    public double getVacuumPerc() {
        return ((this.vacuum / this.maxVacuum) * 100);
    }

    @Override
    public void setHealth(double health) {
        if((health <= this.maxHealth) && (health >= 0)){
            this.health = health;
        } else if (health < 0) {
            this.health = 0;
        } else if (health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }

    public double getHealthPerc() {
        return((this.health / this.maxHealth) * 100);
    }

    public void updatePosition(double newX, double newY, double newAngle) {
        this.posX = newX;
        this.posY = newY;
        this.angle = newAngle;

        this.group.setTranslateX(newX);
        this.group.setTranslateY(newY);

        this.triangle.getPoints().setAll(
                0.0, 0.0,

                (this.vacuumHeight * Math.cos(angle)) - (-vacuumWidth * Math.sin(angle)),
                (this.vacuumHeight * Math.sin(angle)) + (-vacuumWidth * Math.cos(angle)),

                (this.vacuumHeight * Math.cos(angle)) - (vacuumWidth * Math.sin(angle)),
                (this.vacuumHeight * Math.sin(angle)) + (vacuumWidth * Math.cos(angle))
        );
    }

}
