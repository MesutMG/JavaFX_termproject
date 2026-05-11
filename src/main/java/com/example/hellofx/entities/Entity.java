package com.example.hellofx.entities;

import javafx.scene.Group;

public abstract class Entity {
    protected double health;
    protected double maxHealth;
    protected double posX;
    protected double posY;
    protected boolean isAlive;
    protected Group group;
    protected double angle;
    protected double speed;
    protected double attackDamage;

    public Entity(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        this.isAlive = true;
    }

    public double getAngle() { return angle; }
    public void setAngle(double angle) { this.angle = angle; }
    
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }

    public double getAttackDamage() { return attackDamage; }
    public void setAttackDamage(double attackDamage) { this.attackDamage = attackDamage; }

    public double getHealth() { return health; }

    public void setHealth(double health) {
        this.health = Math.max(0, health);
        if (this.health <= 0) {
            this.health = 0;
            this.isAlive = false;
        }
    }

    public double getPosX() { return posX; }
    
    public void setPosX(double posX) {
        this.posX = posX;
        if (this.group != null) this.group.setTranslateX(posX);
    }

    public double getPosY() { return posY; }

    public void setPosY(double posY) {
        this.posY = posY;
        if (this.group != null) this.group.setTranslateY(posY);
    }

    public boolean isAlive() { return isAlive; }
    public void setAlive(boolean alive) { this.isAlive = alive; }

    public Group getGroup() { return group; }
}
