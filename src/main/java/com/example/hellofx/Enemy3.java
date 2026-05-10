package com.example.hellofx;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.Group;

public class Enemy3 {
    private double  health;
    private double  posX;
    private double  posY;
    private boolean isAlive;
    private Circle  circleFace;
    private Circle  circleEye1;
    private Circle circleEye2;
    private Line[]  rays;
    private Group raysGroup;
    private double opacity;

    Enemy3(double posX, double posY){
        this.health     = 100;
        this.posX       = posX;
        this.posY       = posY;
        this.isAlive    = true;

        circleFace = new Circle(20, Color.RED);
        circleFace.setCenterX(posX);
        circleFace.setCenterY(posY);
        circleFace.setOpacity(0.8);
        this.setCircleFace(circleFace);

        circleEye1 = new Circle(3, Color.YELLOW);
        circleEye1.setCenterX(posX + 6);
        circleEye1.setCenterY(posY - 2);
        circleEye1.setOpacity(0.5);
        this.setCircleEye1(circleEye1);

        circleEye2 = new Circle(3, Color.YELLOW);
        circleEye2.setCenterX(posX - 6);
        circleEye2.setCenterY(posY - 2);
        circleEye2.setOpacity(0.5);
        this.setCircleEye2(circleEye2);

        rays = new Line[6];
        double innerRadius = 25;
        double outerRadius = 35;

        for (int i = 0; i < 6; i++) {
            double angle = i * (Math.PI / 3); //60 derece arayla

            double startX = posX + innerRadius * Math.cos(angle);
            double startY = posY + innerRadius * Math.sin(angle);
            double endX   = posX + outerRadius * Math.cos(angle);
            double endY   = posY + outerRadius * Math.sin(angle);

            rays[i] = new Line(startX, startY, endX, endY);
            rays[i].setStroke(Color.RED);
            rays[i].setStrokeWidth(4);
            rays[i].setOpacity(0.8);
        }

        this.raysGroup = new Group(rays);
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
    }

    public Circle getCircleFace() {
        return circleFace;
    }

    public void setCircleFace(Circle circleFace) {
        this.circleFace = circleFace;
    }

    public Circle getCircleEye1() {
        return circleEye1;
    }

    public void setCircleEye1(Circle circleEye1) {
        this.circleEye1 = circleEye1;
    }

    public Circle getCircleEye2() {
        return circleEye2;
    }

    public void setCircleEye2(Circle circleEye2) {
        this.circleEye2 = circleEye2;
    }
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void spinRays() {
        this.raysGroup.setRotate(this.raysGroup.getRotate() + 2); //2 derece donduruyor
    }

    public Node[] draw() {
        return new Node[] {
                this.circleFace,
                this.circleEye1,
                this.circleEye2,
                this.raysGroup
        };
    }

}
