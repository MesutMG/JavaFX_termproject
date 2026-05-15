package com.example.hellofx.tokens;

import com.example.hellofx.ConfigReader;
import com.example.hellofx.entities.Player;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HealthToken extends Token {
    public HealthToken(double posX, double posY) {
        super(posX, posY);

        Circle circle = new Circle(15, Color.LIMEGREEN);
        circle.setStroke(Color.DARKGREEN);
        circle.setStrokeWidth(2);

        Text symbol = new Text("+");
        symbol.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        symbol.setFill(Color.WHITE);
        symbol.setTranslateX(-6);
        symbol.setTranslateY(6);

        this.group = new Group(circle, symbol);
        this.group.setTranslateX(posX);
        this.group.setTranslateY(posY);
    }

    @Override
    public void tokenUsed(Player player, ConfigReader config) {
        player.setHealth(Math.min(player.getHealth() + config.health_token_increase, player.getMaxHealth()));
    }
}
