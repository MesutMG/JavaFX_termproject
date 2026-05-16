package fxproject.entities;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;

public class Ghost extends Enemy {
    private Circle  circle;
    private Circle  eye1;
    private Circle  eye2;
    private Circle  eye10;
    private Circle  eye20;
    private Rectangle rectangle;
    private final int CIRCLE_RADIUS = 15;

    public Ghost(double posX, double posY) {
        super(posX, posY);
        this.maxHealth = 100;
        this.health = this.maxHealth;
        this.score = 10;
        this.attackDamage = 0.5;

        Circle circle = new Circle(CIRCLE_RADIUS, Color.WHITE);
        circle.setCenterX(0);
        circle.setCenterY(0);
        circle.setOpacity(0.8);
        this.setCircle(circle);

        Circle eye1 = new Circle(2, Color.BLACK);
        eye1.setCenterX(-5);
        eye1.setCenterY(1);
        eye1.setOpacity(1);
        this.setEye1(eye1);

        Circle eye2 = new Circle(2, Color.BLACK);
        eye2.setCenterX(5);
        eye2.setCenterY(1);
        eye2.setOpacity(1);
        this.setEye2(eye2);

        Circle eye10 = new Circle(5, Color.WHITE);
        eye10.setCenterX(-5);
        eye10.setCenterY(0);
        eye10.setOpacity(1);
        this.setEye10(eye10);

        Circle eye20 = new Circle(5, Color.WHITE);
        eye20.setCenterX(5);
        eye20.setCenterY(0);
        eye20.setOpacity(1);
        this.setEye20(eye20);

        Rectangle rectangle = new Rectangle(30,15, Color.WHITE);
        rectangle.setY(0);
        rectangle.setX(-CIRCLE_RADIUS);
        rectangle.setOpacity(0.8);
        this.setRectangle(rectangle);
        
        this.group = new Group(this.rectangle, this.circle, this.eye10, this.eye20 ,this.eye1, this.eye2);
        this.group.setTranslateX(posX);
        this.group.setTranslateY(posY);
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Circle getEye1() {
        return eye1;
    }

    public void setEye1(Circle eye1) {
        this.eye1 = eye1;
    }

    public Circle getEye2() {
        return eye2;
    }

    public void setEye2(Circle eye2) {
        this.eye2 = eye2;
    }

    public Circle getEye10() {
        return eye10;
    }

    public void setEye10(Circle eye10) {
        this.eye10 = eye10;
    }

    public Circle getEye20() {
        return eye20;
    }

    public void setEye20(Circle eye20) {
        this.eye20 = eye20;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
