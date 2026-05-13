package com.example.hellofx.levels;

public class LevelThreeScreen extends Level {

    @Override
    public String getLevelTitle() { return "Level 3"; }

    @Override
    public int getTimeLimitMinutes() { return 3; }

    @Override
    public int getTimeLimitSeconds() { return 0; }

    @Override
    public String getBackgroundImagePath() { return "file:img/bg3.jpg"; }

    @Override
    public String[] getOverlayImagePaths() { return new String[]{}; }

    @Override
    public int getGhostCount() { return 4; }

    @Override
    public int getRipperCount() { return 3; }

    @Override
    public int getWispCount() { return 2; }

    @Override
    public int getPlayAreaW() { return 600; }

    @Override
    public int getPlayAreaH() { return 800; }

    @Override
    public Level createNextLevel() { return new LevelOneScreen(); }

    @Override
    public String getNextLevelTitle() { return "Level 1"; }

    @Override
    public Level createRetryLevel() { return new LevelThreeScreen(); }
}
