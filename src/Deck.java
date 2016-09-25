import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Hadassah Harland on 21/09/2016.
 * This class creates a deck object consisting of a random array of 62 card objects for the game
 * "Project Mineral: Super Trump"
 */
public class Deck {
    public Deck() {
        // for each set of card details in the file, create a card object and store in an array of 62 card objects
    }
    public static void loadfromfile() {
        FileSystem fs = FileSystems.getDefault();
        String path = "C:\\Users\\Hadassah\\IdeaProjects\\CP2406-assignment-throwaway\\src\\playCards.txt";
        Path file = fs.getPath(path);
        InputStream input = null;
        int cardIndex = 0;
        int cardDetailIndex = 0;
        int lineNo = 0;
        int LINESINFILE = 702;
        int NOPLAYCARDS = 54;
        int NOTRUMPCARDS = 6;
        // Exception handling required to catch program in the case of an unreadable file
        try {
//            path.getFileSystem().provider().checkAccess(file, READ);
            input = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            for (lineNo = 0; lineNo < LINESINFILE; lineNo++) {
                cardIndex = lineNo/13;
                cardDetailIndex = lineNo%13;
                String s = reader.readLine();
                System.out.println(cardIndex + ", " + cardDetailIndex + ", " + s);

            }
            input.close();
        } catch (IOException e) {
            System.out.println("Error: Data file cannot be read");
        }
    }
    // TODO shuffle deck
    // TODO when card.dealt == true remove from instance of deck
}
