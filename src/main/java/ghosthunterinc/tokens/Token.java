package ghosthunterinc.tokens;

import ghosthunterinc.ConfigReader;
import ghosthunterinc.entities.Player;

import javafx.scene.Group;

public abstract class Token {
    protected double posX;
    protected double posY;
    protected Group group;
    protected double speed;
    protected double angle;
    protected boolean collected = false;

    public Token(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        this.angle = Math.random() * Math.PI * 2;
        this.speed = 2.5;
    }

    public void updatePosition(double newX, double newY, double newAngle) {
        this.posX = newX;
        this.posY = newY;
        this.angle = newAngle;

        if (this.group != null) {
            this.group.setTranslateX(newX);
            this.group.setTranslateY(newY);
        }
    }

    public abstract void tokenUsed(Player player, ConfigReader config);

    public void update() {
    }

    public Group getBody() {
        return group;
    }

    public double getPosX() { return posX; }
    public double getPosY() { return posY; }

    public boolean isCollected() { return collected; }
    public void setCollected(boolean collected) { this.collected = collected; }
}