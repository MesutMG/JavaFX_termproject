package ghosthunterinc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

        } catch (FileNotFoundException e) {
            saveLog(filePath, 0, 1);
            return readLog(filePath);
        }
    }

    public void saveLog(String filePath, int highScore, int lastLevel) {

        if(lastLevel >= 3){
            lastLevel = 3;
        } else if (lastLevel < 1) {
            lastLevel = 1;
        }

        try {
            File file = new File(filePath);
            if (file.getParentFile() != null) { // Add this check
                file.getParentFile().mkdirs();
            }
            FileWriter writer = new FileWriter(file);
            writer.write("high_score: " + highScore + "\n");
            writer.write("last_level: " + lastLevel + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}