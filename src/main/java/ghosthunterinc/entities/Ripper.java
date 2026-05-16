package fxproject.entities;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.Group;

public class Ripper extends Enemy {
    private Circle  circleFace;
    private Circle  circleOuter;
    private Circle  circleEye1;
    private Circle circleEye2;
    private Polygon triangle1;
    private Polygon triangle2;

    public Ripper(double posX, double posY) {
        super(posX, posY);
        super.attackDamage = 3;
        this.maxHealth = 150;
        this.health = this.maxHealth;
        this.score = 20;

        Circle circleFace = new Circle(20, Color.BLACK);
        circleFace.setCenterX(0);
        circleFace.setCenterY(0);
        circleFace.setOpacity(1);
        this.setCircleFace(circleFace);

        Circle circleEye1 = new Circle(3, Color.RED);
        circleEye1.setCenterX(6);
        circleEye1.setCenterY(-2);
        circleEye1.setOpacity(1);
        this.setCircleEye1(circleEye1);

        Circle circleEye2 = new Circle(3, Color.RED);
        circleEye2.setCenterX(-6);
        circleEye2.setCenterY(-2);
        circleEye2.setOpacity(1);
        this.setCircleEye2(circleEye2);

        Circle circleOuter = new Circle(35, Color.PURPLE);
        circleOuter.setCenterX(0);
        circleOuter.setCenterY(0);
        circleOuter.setOpacity(1);
        this.setCircleOuter(circleOuter);

        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(
                0.0, -50.0,
                50.0, 25.0,
                -50.0, 25.0
        );
        triangle1.setFill(Color.PURPLE);
        triangle1.setOpacity(1);
        this.setTriangle1(triangle1);

        Polygon triangle2 = new Polygon();
        triangle2.getPoints().addAll(
                0.0, 50.0,
                50.0, -25.0,
                -50.0, -25.0
        );
        triangle2.setFill(Color.PURPLE);
        triangle2.setOpacity(1);
        this.setTriangle2(triangle2);

        this.group = new Group(this.triangle1, this.triangle2, this.circleOuter,
                this.circleFace, this.circleEye1, this.circleEye2);
        this.group.setTranslateX(posX);
        this.group.setTranslateY(posY);
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
}
