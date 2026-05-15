package com.example.hellofx.tokens;

import com.example.hellofx.ConfigReader;
import com.example.hellofx.entities.Player;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class RangeToken extends Token {
    public RangeToken(double posX, double posY) {
        super(posX, posY);

        Circle circle = new Circle(15, Color.YELLOW);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                -6.0, 0.0,
                4.0, -8.0,
                4.0, 8.0
        );
        triangle.setFill(Color.BLACK);

        this.group = new Group(circle, triangle);
        this.group.setTranslateX(posX);
        this.group.setTranslateY(posY);
    }

    @Override
    public void tokenUsed(Player player, ConfigReader config) {
        player.setVacuumWidth(player.getVacuumWidth() + config.vacuum_token_increase);
    }
}
