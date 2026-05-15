package com.example.hellofx.tokens;

import com.example.hellofx.ConfigReader;
import com.example.hellofx.entities.Player;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class HealthToken extends Token {
    public HealthToken(double posX, double posY) {
        super(posX, posY);

        Circle circle = new Circle(15, Color.YELLOW);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);

        Rectangle vertical = new Rectangle(4, 16, Color.RED);
        vertical.setX(-2);
        vertical.setY(-8);

        Rectangle horizontal = new Rectangle(16, 4, Color.RED);
        horizontal.setX(-8);
        horizontal.setY(-2);

        this.group = new Group(circle, vertical, horizontal);
        this.group.setTranslateX(posX);
        this.group.setTranslateY(posY);
    }

    @Override
    public void tokenUsed(Player player, ConfigReader config) {
        player.setHealth(Math.min(player.getHealth() + config.health_token_increase, player.getMaxHealth()));
    }
}
