package com.example.hellofx;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Enemy2 {
    private double  health;
    private double  posX;
    private double  posY;
    private boolean isAlive;
    private Circle  circleFace;
    private Circle  circleOuter;
    private Circle  circleEye1;
    private Circle circleEye2;
    private Polygon triangle1;
    private Polygon triangle2;
    private double opacity;

    Enemy2(double posX, double posY){
        this.health     = 100;
        this.posX       = posX;
        this.posY       = posY;
        this.isAlive    = true;

        Circle circleFace = new Circle(20, Color.BLACK);
        circleFace.setCenterX(posX);
        circleFace.setCenterY(posY);
        circleFace.setOpacity(1);
        this.setCircleFace(circleFace);

        Circle circleEye1 = new Circle(3, Color.RED);
        circleEye1.setCenterX(posX + 6);
        circleEye1.setCenterY(posY - 2);
        circleEye1.setOpacity(1);
        this.setCircleEye1(circleEye1);

        Circle circleEye2 = new Circle(3, Color.RED);
        circleEye2.setCenterX(posX - 6);
        circleEye2.setCenterY(posY - 2);
        circleEye2.setOpacity(1);
        this.setCircleEye2(circleEye2);

        Circle circleOuter = new Circle(35, Color.PURPLE);
        circleOuter.setCenterX(posX);
        circleOuter.setCenterY(posY);
        circleOuter.setOpacity(1);
        this.setCircleOuter(circleOuter);

        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(
                posX, posY - 50,
                posX + 50, posY + 25,
                posX - 50, posY + 25
        );
        triangle1.setFill(Color.PURPLE);
        triangle1.setOpacity(1);
        this.setTriangle1(triangle1);

        Polygon triangle2 = new Polygon();
        triangle2.getPoints().addAll(
                posX, posY + 50,
                posX + 50, posY - 25,
                posX - 50, posY - 25
        );
        triangle2.setFill(Color.PURPLE);
        triangle2.setOpacity(1);
        this.setTriangle2(triangle2);

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

    public Circle getCircleOuter() {
        return circleOuter;
    }

    public void setCircleOuter(Circle circleOuter) {
        this.circleOuter = circleOuter;
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

    public Polygon getTriangle1() {
        return triangle1;
    }

    public void setTriangle1(Polygon triangle1) {
        this.triangle1 = triangle1;
    }

    public Polygon getTriangle2() {
        return triangle2;
    }

    public void setTriangle2(Polygon triangle2) {
        this.triangle2 = triangle2;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Node[] draw() {
        return new Node[]{this.triangle1, this.triangle2, this.circleOuter,
                        this.circleFace, this.circleEye1, this.circleEye2};
    }

}
