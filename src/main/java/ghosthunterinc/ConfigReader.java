package fxproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConfigReader {

    public int maximum_health;
    public int maximum_vacuum;
    public int entity_damage;
    public int vacuum_decrease;
    public int vacuum_increase;

    public int level_1_playable_area_x;
    public int level_1_playable_area_y;
    public int level_1_playable_area_width;
    public int level_1_playable_area_height;
    public int level_1_time;
    public int level_1_ghosts;
    public int level_1_rippers;
    public int level_1_wisps;

    public int level_2_playable_area_x;
    public int level_2_playable_area_y;
    public int level_2_playable_area_width;
    public int level_2_playable_area_height;
    public int level_2_time;
    public int level_2_ghosts;
    public int level_2_rippers;
    public int level_2_wisps;

    public int level_3_playable_area_x;
    public int level_3_playable_area_y;
    public int level_3_playable_area_width;
    public int level_3_playable_area_height;
    public int level_3_time;
    public int level_3_ghosts;
    public int level_3_rippers;
    public int level_3_wisps;

    public int health_token_increase;
    public int vacuum_token_increase;
    public int eye_token_duration;

    public boolean readConfig(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(":\\s+");

                if (parts.length == 2) {
                    String key = parts[0];
                    int value = Integer.parseInt(parts[1]);

                    switch (key) {
                        case "maximum_health": maximum_health = value; break;
                        case "maximum_vacuum": maximum_vacuum = value; break;
                        case "entity_damage": entity_damage = value; break;
                        case "vacuum_decrease": vacuum_decrease = value; break;
                        case "vacuum increase": vacuum_increase = value; break;
                        case "level_1_playable_area_x": level_1_playable_area_x = value; break;
                        case "level_1_playable_area_y": level_1_playable_area_y = value; break;
                        case "level_1_playable_area_width": level_1_playable_area_width = value; break;
                        case "level_1_playable_area_height": level_1_playable_area_height = value; break;
                        case "level_1_time": level_1_time = value; break;
                        case "level_1_ghosts": level_1_ghosts = value; break;
                        case "level_1_rippers": level_1_rippers = value; break;
                        case "level_1_wisps": level_1_wisps = value; break;
                        case "level_2_playable_area_x": level_2_playable_area_x = value; break;
                        case "level_2_playable_area_y": level_2_playable_area_y = value; break;
                        case "level_2_playable_area_width": level_2_playable_area_width = value; break;
                        case "level_2_playable_area_height": level_2_playable_area_height = value; break;
                        case "level_2_time": level_2_time = value; break;
                        case "level_2_ghosts": level_2_ghosts = value; break;
                        case "level_2_rippers": level_2_rippers = value; break;
                        case "level_2_wisps": level_2_wisps = value; break;
                        case "level_3_playable_area_x": level_3_playable_area_x = value; break;
                        case "level_3_playable_area_y": level_3_playable_area_y = value; break;
                        case "level_3_playable_area_width": level_3_playable_area_width = value; break;
                        case "level_3_playable_area_height": level_3_playable_area_height = value; break;
                        case "level_3_time": level_3_time = value; break;
                        case "level_3_ghosts": level_3_ghosts = value; break;
                        case "level_3_rippers": level_3_rippers = value; break;
                        case "level_3_wisps": level_3_wisps = value; break;
                        case "health_token_increase": health_token_increase = value; break;
                        case "vacuum_token_increase": vacuum_token_increase = value; break;
                        case "eye_token_duration": eye_token_duration = value; break;
                    }
                }
            }
            scanner.close();
            return false;

        } catch (FileNotFoundException e) {
            writeDefaultConfig(filePath);
            return readConfig(filePath);
        }
    }

    private void writeDefaultConfig(String filePath) {
        String defaultConfig = "maximum_health: 100\n" +
                "maximum_vacuum: 100\n" +
                "entity_damage: 10\n" +
                "vacuum_decrease: 1\n" +
                "vacuum increase: 2\n" +
                "\n" +
                "level_1_playable_area_x: 200\n" +
                "level_1_playable_area_y: 140\n" +
                "level_1_playable_area_width: 1050\n" +
                "level_1_playable_area_height: 560\n" +
                "level_1_time: 60\n" +
                "level_1_ghosts: 5\n" +
                "level_1_rippers: 0\n" +
                "level_1_wisps: 0\n" +
                "\n" +
                "level_2_playable_area_x: 200\n" +
                "level_2_playable_area_y: 100\n" +
                "level_2_playable_area_width: 1000\n" +
                "level_2_playable_area_height: 560\n" +
                "level_2_time: 120\n" +
                "level_2_ghosts: 4\n" +
                "level_2_rippers: 3\n" +
                "level_2_wisps: 0\n" +
                "\n" +
                "level_3_playable_area_x: 200\n" +
                "level_3_playable_area_y: 0\n" +
                "level_3_playable_area_width: 800\n" +
                "level_3_playable_area_height: 700\n" +
                "level_3_time: 180\n" +
                "level_3_ghosts: 4\n" +
                "level_3_rippers: 3\n" +
                "level_3_wisps: 2\n" +
                "\n" +
                "health_token_increase: 20\n" +
                "vacuum_token_increase: 20\n" +
                "eye_token_duration: 5\n";

        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            writer.write(defaultConfig);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCustomConfig(String filePath) {
        StringBuilder configText = new StringBuilder();

        configText.append("maximum_health: ").append(maximum_health).append("\n");
        configText.append("maximum_vacuum: ").append(maximum_vacuum).append("\n");
        configText.append("entity_damage: ").append(entity_damage).append("\n");
        configText.append("vacuum_decrease: ").append(vacuum_decrease).append("\n");
        configText.append("vacuum increase: ").append(vacuum_increase).append("\n\n");
        configText.append("level_1_playable_area_x: ").append(level_1_playable_area_x).append("\n");
        configText.append("level_1_playable_area_y: ").append(level_1_playable_area_y).append("\n");
        configText.append("level_1_playable_area_width: ").append(level_1_playable_area_width).append("\n");
        configText.append("level_1_playable_area_height: ").append(level_1_playable_area_height).append("\n");
        configText.append("level_1_time: ").append(level_1_time).append("\n");
        configText.append("level_1_ghosts: ").append(level_1_ghosts).append("\n");
        configText.append("level_1_rippers: ").append(level_1_rippers).append("\n");
        configText.append("level_1_wisps: ").append(level_1_wisps).append("\n\n");
        configText.append("level_2_playable_area_x: ").append(level_2_playable_area_x).append("\n");
        configText.append("level_2_playable_area_y: ").append(level_2_playable_area_y).append("\n");
        configText.append("level_2_playable_area_width: ").append(level_2_playable_area_width).append("\n");
        configText.append("level_2_playable_area_height: ").append(level_2_playable_area_height).append("\n");
        configText.append("level_2_time: ").append(level_2_time).append("\n");
        configText.append("level_2_ghosts: ").append(level_2_ghosts).append("\n");
        configText.append("level_2_rippers: ").append(level_2_rippers).append("\n");
        configText.append("level_2_wisps: ").append(level_2_wisps).append("\n\n");
        configText.append("level_3_playable_area_x: ").append(level_3_playable_area_x).append("\n");
        configText.append("level_3_playable_area_y: ").append(level_3_playable_area_y).append("\n");
        configText.append("level_3_playable_area_width: ").append(level_3_playable_area_width).append("\n");
        configText.append("level_3_playable_area_height: ").append(level_3_playable_area_height).append("\n");
        configText.append("level_3_time: ").append(level_3_time).append("\n");
        configText.append("level_3_ghosts: ").append(level_3_ghosts).append("\n");
        configText.append("level_3_rippers: ").append(level_3_rippers).append("\n");
        configText.append("level_3_wisps: ").append(level_3_wisps).append("\n\n");
        configText.append("health_token_increase: ").append(health_token_increase).append("\n");
        configText.append("vacuum_token_increase: ").append(vacuum_token_increase).append("\n");
        configText.append("eye_token_duration: ").append(eye_token_duration).append("\n");

        try {
            File file = new File(filePath);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            FileWriter writer = new FileWriter(file);
            writer.write(configText.toString());
            writer.close();
            System.out.println("Config successfully saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}