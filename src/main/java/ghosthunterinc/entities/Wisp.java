package ghosthunterinc.entities;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.Group;

public class Wisp extends Enemy {
    private Circle  circleFace;
    private Circle  circleEye1;
    private Circle circleEye2;
    private Line[]  rays;
    private Group raysGroup;

    public Wisp(double posX, double posY) {
        super(posX, posY);
        super.attackDamage = 5;
        this.maxHealth = 200;
        this.health = this.maxHealth;
        this.score = 30;

        circleFace = new Circle(20, Color.RED);
        circleFace.setCenterX(0);
        circleFace.setCenterY(0);
        circleFace.setOpacity(0.8);
        this.setCircleFace(circleFace);

        circleEye1 = new Circle(3, Color.YELLOW);
        circleEye1.setCenterX(6);
        circleEye1.setCenterY(-2);
        circleEye1.setOpacity(0.5);
        this.setCircleEye1(circleEye1);

        circleEye2 = new Circle(3, Color.YELLOW);
        circleEye2.setCenterX(-6);
        circleEye2.setCenterY(-2);
        circleEye2.setOpacity(0.5);
        this.setCircleEye2(circleEye2);

        rays = new Line[6];
        double innerRadius = 30;
        double outerRadius = 40;

        for (int i = 0; i < 6; i++) {
            double angle = i * (Math.PI / 3); // 60 derece arayla

            double startX = innerRadius * Math.cos(angle);
            double startY = innerRadius * Math.sin(angle);
            double endX   = outerRadius * Math.cos(angle);
            double endY   = outerRadius * Math.sin(angle);

            rays[i] = new Line(startX, startY, endX, endY);
            rays[i].setStroke(Color.RED);
            rays[i].setStrokeWidth(4);
            rays[i].setOpacity(0.8);
        }

        this.raysGroup = new Group(rays);

        this.group = new Group(this.circleFace, this.circleEye1, this.circleEye2, this.raysGroup);
        this.group.setTranslateX(posX);
        this.group.setTranslateY(posY);
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

    @Override
    public void update() {
        this.raysGroup.setRotate(this.raysGroup.getRotate() + 1); //1 derece donduruyor
    }

}
