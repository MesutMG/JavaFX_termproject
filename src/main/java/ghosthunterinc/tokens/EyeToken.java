package fxproject.tokens;

import fxproject.ConfigReader;
import fxproject.entities.Player;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class EyeToken extends Token {
    public EyeToken(double posX, double posY) {
        super(posX, posY);

        Circle circle = new Circle(15, Color.YELLOW);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);

        Ellipse eyeWhite = new Ellipse(0, 0, 10, 6);
        eyeWhite.setFill(Color.WHITE);
        eyeWhite.setStroke(Color.BLACK);
        eyeWhite.setStrokeWidth(0.5);

        Circle iris = new Circle(4, Color.BLUE);
        Circle pupil = new Circle(2, Color.BLACK);

        this.group = new Group(circle, eyeWhite, iris, pupil);
        this.group.setTranslateX(posX);
        this.group.setTranslateY(posY);
    }

    @Override
    public void tokenUsed(Player player, ConfigReader config) {
        // Eye reveal effect is handled in Level Class
    }
}
