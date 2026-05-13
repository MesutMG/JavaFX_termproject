package com.example.hellofx.tokens;

import com.example.hellofx.entities.Player;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class EyeToken extends Token {
    public EyeToken(double posX, double posY) {
        super(posX, posY);

        Circle circle = new Circle(15, Color.GOLD);
        circle.setStroke(Color.DARKGOLDENROD);
        circle.setStrokeWidth(2);

        Text symbol = new Text("E");
        symbol.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        symbol.setFill(Color.WHITE);
        symbol.setTranslateX(-5);
        symbol.setTranslateY(6);

        this.group = new Group(circle, symbol);
        this.group.setTranslateX(posX);
        this.group.setTranslateY(posY);
    }

    @Override
    public void tokenUsed(Player player) {
        // Eye reveal effect is handled in Level Class
    }
}
