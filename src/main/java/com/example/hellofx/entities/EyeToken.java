package com.example.hellofx.entities;

public class EyeToken extends Token{
    public EyeToken(double posX, double posY){
        super(posX, posY);
    }

    @Override
    public void tokenUsed(Player player){
        player.setHealth(player.getHealth() + 10);
    }
}
