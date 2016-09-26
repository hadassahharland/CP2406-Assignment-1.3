package src;

import javax.lang.model.element.Element;
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
import java.util.Collection;
import java.util.Collections;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

/**
 * Created by Hadassah Harland on 21/09/2016.
 * This class creates a deck object consisting of a random array of 62 card objects for the game
 * "Project Mineral: Super Trump"
 */
public class Deck {
    public static Card[] deck;
    ArrayList<Card> playDeck;
    int NOPLAYCARDS = 54;
    int NOTRUMPCARDS = 6;
    //String[] playCardData;

    public Deck() {
        // for each set of card details in the file, create a card object and store in an array of 60 card objects
        deck = new Card[(NOPLAYCARDS+NOPLAYCARDS)];
        loadfromfile();
        shuffleDeck();
    }

    public void loadfromfile() {
        FileSystem fs = FileSystems.getDefault();
        String path = "C:\\Users\\Hadassah\\IdeaProjects\\CP2406-Assignment-1.3\\src\\src\\playCards.txt";
        Path file = fs.getPath(path);
        InputStream input;

        // Constants for playCards file
        int LINESINPLAYFILE = 702;
        int LINESPERPLAYCARD = 13;

        String[] playCardData = new String[LINESINPLAYFILE];
        // Exception handling required to catch program in the case of an unreadable file
        try {
//            path.getFileSystem().provider().checkAccess(file, READ);
            input = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            for (int lineNo = 0; lineNo < LINESINPLAYFILE; lineNo++) {
                String lineContent = reader.readLine();
                // save each line of the file to an object in array
                playCardData[lineNo] = lineContent;
            }
            input.close();
        } catch (IOException e) {
            System.out.println("Error: Data file 'playCards.txt' cannot be read");
        }
        for (int cardIndex = 0; cardIndex < NOPLAYCARDS; cardIndex++)  {
            int startIndex = cardIndex*LINESPERPLAYCARD;
            String[] temp = new String[LINESPERPLAYCARD];
            System.arraycopy(playCardData,startIndex,temp,0,LINESPERPLAYCARD);
            // generate new card using the data from the file
            deck[cardIndex] = new PlayCard(cardIndex,temp[1],temp[2],temp[3],temp[4],temp[5],temp[6],temp[7],
                    temp[8],temp[9],temp[10],temp[11],temp[12]);
            //System.out.println(cardIndex + deck[cardIndex].toString());
        }

        String path2 = "C:\\Users\\Hadassah\\IdeaProjects\\CP2406-Assignment-1.3\\src\\src\\trumpCards.txt";
        Path file2 = fs.getPath(path2);


        // Constants for trumpCards file
        int LINESINTRUMPFILE = 30;
        int LINESPERTRUMPCARD = 5;

        String[] trumpCardData = new String[LINESINTRUMPFILE];
        // Exception handling required to catch program in the case of an unreadable file
        try {
//            path.getFileSystem().provider().checkAccess(file, READ);
            input = Files.newInputStream(file2);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            for (int lineNo = 0; lineNo < LINESINTRUMPFILE; lineNo++) {
                String lineContent = reader.readLine();
                // save each line of the file to an object in array
                trumpCardData[lineNo] = lineContent;
            }
            input.close();
        } catch (IOException e) {
            System.out.println("Error: Data file 'trumpCards.txt' cannot be read");
        }
        for (int cardIndex = NOPLAYCARDS; cardIndex < NOPLAYCARDS+NOTRUMPCARDS; cardIndex++)  {
            int startIndex = (cardIndex-NOPLAYCARDS)*LINESPERTRUMPCARD;
            String[] temp = new String[LINESPERTRUMPCARD];
            System.arraycopy(trumpCardData,startIndex,temp,0,LINESPERTRUMPCARD);
            // generate new card using the data from the file
            deck[cardIndex] = new TrumpCard(cardIndex,temp[1],temp[2],temp[3],temp[4]);
            //System.out.println(cardIndex + deck[cardIndex].toString());
        }
    }

    public void shuffleDeck()  {
        // generates a mutable ArrayList containing the elements of deck in a random order
        ArrayList<Card> arrayList = new ArrayList<>(Arrays.asList(deck));
        Collections.shuffle(arrayList);
        this.playDeck = arrayList;
    }

    public void removeCard(int cardIndex)  {
        for (int i = 0; i < length(playDeck); i++)  {
            if (playDeck.get(i).cardIndex == cardIndex)  {
                playDeck.remove(i);
            }
        }
    }
}
