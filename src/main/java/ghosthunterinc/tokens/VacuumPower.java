package fxproject.tokens;

import fxproject.ConfigReader;
import fxproject.entities.Player;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class VacuumPower extends Token {
    public VacuumPower(double posX, double posY) {
        super(posX, posY);

        Circle circle = new Circle(15, Color.DODGERBLUE);
        circle.setStroke(Color.DARKBLUE);
        circle.setStrokeWidth(2);

        Text symbol = new Text("P");
        symbol.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        symbol.setFill(Color.BLACK);
        symbol.setTranslateX(-6);
        symbol.setTranslateY(6);

        this.group = new Group(circle, symbol);
        this.group.setTranslateX(posX);
        this.group.setTranslateY(posY);
    }

    @Override
    public void tokenUsed(Player player, ConfigReader config) {
        player.setAttackDamage(player.getAttackDamage() + 0.5);
    }
}
