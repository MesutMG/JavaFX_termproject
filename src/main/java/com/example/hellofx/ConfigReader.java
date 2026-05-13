package com.example.hellofx;

import java.io.File;
import java.io.FileNotFoundException;
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

        } catch (FileNotFoundException e) {return true;}
    }
}