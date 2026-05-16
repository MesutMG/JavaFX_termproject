package ghosthunterinc.levels;

public class LevelTwoScreen extends Level {

    @Override
    public String getLevelTitle() { return "Level 2"; }

    @Override
    public int getTimeLimitMinutes() { return (config.level_2_time / 60); }

    @Override
    public int getTimeLimitSeconds() { return (config.level_2_time % 60); }

    @Override
    public String getBackgroundImagePath() { return "/img/bg20.png"; }

    @Override
    public String[] getOverlayImagePaths() {
        return new String[]{"/img/bg21.png"};
    }

    @Override
    public int getGhostCount() { return config.level_2_ghosts; }

    @Override
    public int getRipperCount() { return config.level_2_rippers; }

    @Override
    public int getWispCount() { return config.level_2_wisps; }

    @Override
    public int getPlayAreaX() { return config.level_2_playable_area_x; }

    @Override
    public int getPlayAreaY() { return config.level_2_playable_area_y; }

    @Override
    public int getPlayAreaW() { return config.level_2_playable_area_width; }

    @Override
    public int getPlayAreaH() { return config.level_2_playable_area_height; }

    @Override
    public Level createNextLevel() { return new LevelThreeScreen(); }

    @Override
    public String getNextLevelTitle() { return "Level 3"; }

    @Override
    public Level createRetryLevel() { return new LevelTwoScreen(); }

    @Override
    public int getLevelNumber() { return 2; }
}
