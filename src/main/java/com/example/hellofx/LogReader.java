package com.example.hellofx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LogReader {

    public int high_score;
    public int last_level;


    public boolean readLog(String filePath) {
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
                    int value = Integer.parseInt(parts[1]);;

                    switch (key) {
                        case "high_score": high_score = value; break;
                        case "last_level": last_level = value; break;
                    }
                }
            }
            scanner.close();
            return false;

        } catch (FileNotFoundException e) {return true;}
    }
}