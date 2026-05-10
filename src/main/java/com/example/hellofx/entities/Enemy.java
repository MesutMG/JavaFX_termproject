package com.example.hellofx.entities;

import javafx.scene.Node;
import javafx.scene.Group;

public abstract class Enemy extends Entity {
    protected double  attackDamage;

    public Enemy(double posX, double posY) {
        super(posX, posY);
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

        if (this.health <= 0) {
            this.health = 0;
            this.isAlive = false;
        }
    }

    public double getAttackDamage() {
        return attackDamage;
    }

    public void update() {}

    public Group getBody() {
        return group;
    }
}
