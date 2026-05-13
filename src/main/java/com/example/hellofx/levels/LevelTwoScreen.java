package com.example.hellofx.levels;

public class LevelTwoScreen extends Level {

    @Override
    public String getLevelTitle() { return "Level 2"; }

    @Override
    public int getTimeLimitMinutes() { return 3; }

    @Override
    public int getTimeLimitSeconds() { return 0; }

    @Override
    public String getBackgroundImagePath() { return "file:img/bg2.jpg"; }

    @Override
    public String[] getOverlayImagePaths() { return new String[]{}; }

    @Override
    public int getGhostCount() { return 4; }

    @Override
    public int getRipperCount() { return 3; }

    @Override
    public int getWispCount() { return 0; }

    @Override
    public int getPlayAreaW() { return 700; }

    @Override
    public int getPlayAreaH() { return 700; }

    @Override
    public Level createNextLevel() { return new LevelThreeScreen(); }

    @Override
    public String getNextLevelTitle() { return "Level 3"; }

    @Override
    public Level createRetryLevel() { return new LevelTwoScreen(); }
}
