package fxproject.levels;

public class LevelOneScreen extends Level {

    @Override
    public String getLevelTitle() { return "Level 1"; }

    @Override
    public int getTimeLimitMinutes() { return (config.level_1_time / 60); }

    @Override
    public int getTimeLimitSeconds() { return (config.level_1_time % 60); }

    @Override
    public String getBackgroundImagePath() { return "file:img/bg10.png"; }

    @Override
    public String[] getOverlayImagePaths() {
        return new String[]{"file:img/bg11.png"};
    }

    @Override
    public int getGhostCount() { return config.level_1_ghosts; }

    @Override
    public int getRipperCount() { return config.level_1_rippers; }

    @Override
    public int getWispCount() { return config.level_1_wisps; }

    @Override
    public int getPlayAreaX() { return config.level_1_playable_area_x; }

    @Override
    public int getPlayAreaY() { return config.level_1_playable_area_y; }

    @Override
    public int getPlayAreaW() { return config.level_1_playable_area_width; }

    @Override
    public int getPlayAreaH() { return config.level_1_playable_area_height; }

    @Override
    public Level createNextLevel() { return new LevelTwoScreen(); }

    @Override
    public String getNextLevelTitle() { return "Level 2"; }

    @Override
    public Level createRetryLevel() { return new LevelOneScreen(); }

    @Override
    public int getLevelNumber() { return 1; }
}
