package com.example.hellofx.entities;

public class RangeToken extends Token{
    public RangeToken(double posX, double posY){
        super(posX, posY);
    }

    @Override
    public void tokenUsed(Player player){
        player.setHealth(player.getHealth() + 10);
    }
}
