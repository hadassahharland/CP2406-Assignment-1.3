import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Hadassah Harland on 21/09/2016.
 * This class creates a cards object consisting of a random array of 62 card objects for the game
 * "Project Mineral: Super Trump"
 */
public class Deck {
    public static Card[] cards;
    ArrayList<Card> playDeck;
    final int NO_PLAY_CARDS = 54;
    final int NO_TRUMP_CARDS = 6;

    public Deck() {
        /* for each set of card details in the file, create a card object and store in an array of 60 card objects */
        cards = new Card[(NO_PLAY_CARDS + NO_TRUMP_CARDS)];
        loadFromFile();
        //SuperTrumpGUI.loadCardImages();
        shuffleDeck();
    }

    public void loadFromFile() {
        FileSystem fs = FileSystems.getDefault();
        String path = "playCards.txt";
        Path file = fs.getPath(path);
        InputStream input;

        // Constants for playCards file
        final int LINES_IN_PLAY_FILE = 702;
        final int LINES_PER_PLAY_CARD = 13;

        String[] playCardData = new String[LINES_IN_PLAY_FILE];
        /* Exception handling required to catch program in the case of an unreadable file */
        try {
            input = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            for (int lineNo = 0; lineNo < LINES_IN_PLAY_FILE; lineNo++) {
                String lineContent = reader.readLine();
                // save each line of the file to an object in array
                playCardData[lineNo] = lineContent;
            }
            input.close();
        } catch (IOException e) {
            System.out.println("Error: Data file 'playCards.txt' cannot be read");
        }
        for (int cardIndex = 0; cardIndex < NO_PLAY_CARDS; cardIndex++) {
            int startIndex = cardIndex * LINES_PER_PLAY_CARD;
            String[] temp = new String[LINES_PER_PLAY_CARD];
            System.arraycopy(playCardData, startIndex, temp, 0, LINES_PER_PLAY_CARD);
            // generate new card using the data from the file
            cards[cardIndex] = new PlayCard(cardIndex, temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7],
                    temp[8], temp[9], temp[10], temp[11], temp[12]);
        }

        String path2 = "trumpCards.txt";
        Path file2 = fs.getPath(path2);


        // Constants for trumpCards file
        final int LINES_IN_TRUMP_FILE = 30;
        final int LINES_PER_TRUMP_CARD = 5;

        String[] trumpCardData = new String[LINES_IN_TRUMP_FILE];
        /* Exception handling required to catch program in the case of an unreadable file */
        try {
            input = Files.newInputStream(file2);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            for (int lineNo = 0; lineNo < LINES_IN_TRUMP_FILE; lineNo++) {
                String lineContent = reader.readLine();
                // save each line of the file to an object in array
                trumpCardData[lineNo] = lineContent;
            }
            input.close();
        } catch (IOException e) {
            System.out.println("Error: Data file 'trumpCards.txt' cannot be read");
        }
        for (int cardIndex = NO_PLAY_CARDS; cardIndex < NO_PLAY_CARDS + NO_TRUMP_CARDS; cardIndex++) {
            int startIndex = (cardIndex - NO_PLAY_CARDS) * LINES_PER_TRUMP_CARD;
            String[] temp = new String[LINES_PER_TRUMP_CARD];
            System.arraycopy(trumpCardData, startIndex, temp, 0, LINES_PER_TRUMP_CARD);
            // generate new card using the data from the file
            cards[cardIndex] = new TrumpCard(cardIndex, temp[1], temp[2], temp[3], temp[4]);
        }
    }

    public void shuffleDeck() {
        /* generates a mutable ArrayList containing the elements of cards in a random order */
        ArrayList<Card> arrayList = new ArrayList<>(Arrays.asList(cards));
        Collections.shuffle(arrayList);
        this.playDeck = arrayList;
    }
}