package com.example.hellofx.entities;

import javafx.scene.Group;

public abstract class Enemy extends Entity {
    public Enemy(double posX, double posY) {
        super(posX, posY);
        this.angle = Math.random() * Math.PI * 2;
        this.speed = Math.random() * 2;
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

    public double getHealth() {
        return health;
    }

    @Override
    public void setHealth(double health) {
        super.setHealth(health);
        double scale = this.health / this.maxHealth;
        
        if (this.group != null) {
            this.group.setScaleX(scale);
            this.group.setScaleY(scale);
        }

        if (this.health <= 30) { //30%
            this.health = 0;
            this.isAlive = false;
        }
    }

    public void update() {}

    public Group getBody() {
        return group;
    }
}
