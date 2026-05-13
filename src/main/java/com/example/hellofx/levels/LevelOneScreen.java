package com.example.hellofx.levels;

public class LevelOneScreen extends Level {

    @Override
    public String getLevelTitle() { return "Level 1"; }

    @Override
    public int getTimeLimitMinutes() { return 1; }

    @Override
    public int getTimeLimitSeconds() { return 0; }

    @Override
    public String getBackgroundImagePath() { return "file:img/bg30.png"; }

    @Override
    public String[] getOverlayImagePaths() {
        return new String[]{"file:img/bg31.png", "file:img/bg32.png"};
    }

    @Override
    public int getGhostCount() { return 5; }

    @Override
    public int getRipperCount() { return 0; }

    @Override
    public int getWispCount() { return 0; }

    @Override
    public int getPlayAreaW() { return 800; }

    @Override
    public int getPlayAreaH() { return 600; }

    @Override
    public Level createNextLevel() { return new LevelTwoScreen(); }

    @Override
    public String getNextLevelTitle() { return "Level 2"; }

    @Override
    public Level createRetryLevel() { return new LevelOneScreen(); }
}
