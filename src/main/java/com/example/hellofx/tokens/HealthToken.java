package com.example.hellofx.tokens;

import com.example.hellofx.entities.Player;

public class HealthToken extends Token{
    public HealthToken(double posX, double posY){
        super(posX, posY);
    }

    @Override
    public void tokenUsed(Player player){
        player.setHealth(player.getHealth() + 10);
    }
}
