package fxproject.levels;

public class LevelThreeScreen extends Level {

    @Override
    public String getLevelTitle() { return "Level 3"; }

    @Override
    public int getTimeLimitMinutes() { return (config.level_3_time / 60); }

    @Override
    public int getTimeLimitSeconds() { return (config.level_3_time % 60); }

    @Override
    public String getBackgroundImagePath() { return "file:img/bg30.png"; }

    @Override
    public String[] getOverlayImagePaths() {
        return new String[]{"file:img/bg31.png", "file:img/bg32.png"};
    }

    @Override
    public int getGhostCount() { return config.level_3_ghosts; }

    @Override
    public int getRipperCount() { return config.level_3_rippers; }

    @Override
    public int getWispCount() { return config.level_3_wisps; }

    @Override
    public int getPlayAreaX() { return config.level_3_playable_area_x; }

    @Override
    public int getPlayAreaY() { return config.level_3_playable_area_y; }

    @Override
    public int getPlayAreaW() { return config.level_3_playable_area_width; }

    @Override
    public int getPlayAreaH() { return config.level_3_playable_area_height; }

    @Override
    public Level createNextLevel() { return new LevelOneScreen(); }

    @Override
    public String getNextLevelTitle() { return "Level 1"; }

    @Override
    public Level createRetryLevel() { return new LevelThreeScreen(); }

    @Override
    public int getLevelNumber() { return 3; }
}
