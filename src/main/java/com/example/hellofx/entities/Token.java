package com.example.hellofx.entities;

import javafx.scene.Group;

public abstract class Token {
    protected double posX;
    protected double posY;
    protected Group group;
    protected double speed;

    public Token(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        this.speed = 2.5;
    }

    public void updatePosition(double newX, double newY, double newAngle) {
        this.posX = newX;
        this.posY = newY;

        if (this.group != null) {
            this.group.setTranslateX(newX);
            this.group.setTranslateY(newY);
        }
    }

    public void tokenUsed(Player player) {
        return;
    }

    public void update() {
    }

    public Group getBody() {
        return group;
    }
}