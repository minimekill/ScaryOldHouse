package old80house.modules;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Highscore {

    private String playerpoints;
    private String playername;

    // Magic in
    public void FileWriter(String x) throws IOException {
        String msg = x + System.lineSeparator();
        Files.write(Paths.get("highscore.txt"), msg.getBytes(), StandardOpenOption.APPEND);
    }

    // Magic out
    public void FileReader() throws IOException {

        System.out.println("*****HIGHSCORE***** \n" + "-------------------");

        Scanner db = new Scanner(new File("highscore.txt")); // Put txt content into object

        int count = 1;
        ArrayList<String> points = new ArrayList<>();

        while (db.hasNext()) { // iterates each line in the file
            String line = db.nextLine();

            count++;

            // Collect odds and even entries in object
            if (count % 2 == 0) {
                playername = line;
            } else {
                playerpoints = line;
                points.add(playerpoints + " - " + playername);
            }

        }

        // Sort and reverse point entries
        Collections.sort(points);
        Collections.reverse(points);

        // Print list in correct order
        for (int i = 0; i < points.size(); i++) {
            System.out.println(points.get(i));
        }

        db.close(); // Close resource leaks
    }
}
