package src;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

/**
 * Created by Hadassah on 22/09/2016.
 * This class inherits from the abstract class Player to implement methods using user input.
 */
public class UserControlledPlayer extends Player {
    Card inPlay;

    public UserControlledPlayer(int playerIndex, String playerName)  {
        super(playerIndex, playerName);
    }

    public void showHand() {
        super.hand.show();
    }

    public void takeTurn(Card lastCard) {
        System.out.println("***take turn***");

    }

    public int newRound()  {
        // User decision: By input.
        showHand();
        // Determine play category
        boolean confirm = false;
        int currentCategoryIndex = 0;           // TODO variable might not have been initialized in loop error
        while (!confirm) {
            System.out.println("Choose a play category to start: \n (1) Hardness \n (2) Specific Gravity \n (3) Cleavage " +
                    "\n (4) Crystal Abundance \n (5) Economic Value");
            Scanner inputDevice = new Scanner(System.in);
            String input = inputDevice.next();
            if (input.equals("1")) {
                currentCategoryIndex = 0;
                confirm = true;
            } else if (input.equals("2")) {
                currentCategoryIndex = 1;
                confirm = true;
            } else if (input.equals("3")) {
                currentCategoryIndex = 2;
                confirm = true;
            } else if (input.equals("4")) {
                currentCategoryIndex = 3;
                confirm = true;
            } else if (input.equals("5")) {
                currentCategoryIndex = 4;
                confirm = true;
            } else {
                System.out.println("Invalid input");
                confirm = false;
            }
        }
        confirm = false;
        while (!confirm)  {
            System.out.println("Choose a card from your hand to play");
            Scanner inputDevice = new Scanner(System.in);
            String input = inputDevice.next();
            if (true); {}  //TODO if input is number && input < hand.size(), choose that card else new input.
        }
        // remove the card from the player's hand
        super.hand.removeCard(inPlay);
        return currentCategoryIndex;
    }

    public Card getInPlay() {
        return this.inPlay;
    }
}
