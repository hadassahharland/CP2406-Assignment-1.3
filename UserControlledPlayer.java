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

    public void takeTurn(Card lastCard, int currentCategoryIndex) {
        // User decision: play any card as indicated in hand or pass
        String[] categories = {"hardness", "specific gravity", "cleavage", "crustal abundance", "economic value"};
        System.out.println("The play category is " + categories[currentCategoryIndex]);
        showHand();
        boolean confirm = false, cardPlayed = false;
        while (!confirm)  {
            System.out.println("Choose a card from your hand to play, (0) to show hand or (00) to pass");
            Scanner inputDevice = new Scanner(System.in);
            String input = inputDevice.next();
            if (input.equals("0"))  {  super.hand.show();  }
            else if (input.equals("00"))  {
                confirm = true;
                super.pass();
            }
            else {
                for (int i = 1; i <= super.hand.hand.size(); i++) {
                    // convert index to string to compare to input
                    String index = Integer.toString(i);
                    if (input.equals(index)) {
                        Card card = super.hand.hand.get(i-1);
                        if (card.validPlay(lastCard, currentCategoryIndex)) {
                            inPlay = super.hand.hand.get(i - 1);
                            cardPlayed = true;
                            confirm = true;
                        }
                        else  {
                            // occurs if the card selected is a play card that is not higher in the play category
                            System.out.println("The card you selected is not a valid play");
                        }
                    }
                }
                if (!confirm) {
                    System.out.println("The card you selected was not found in your hand");
                }
            }
        }
        // remove the card from the player's hand
        if (cardPlayed)  {  super.hand.removeCard(inPlay); }
    }

    public int newRound()  {
        // User decision: By input.
        showHand();
        boolean confirm = false;
        while (!confirm)  {
            System.out.println("Choose a card from your hand to play, or (0) to show hand");
            Scanner inputDevice = new Scanner(System.in);
            String input = inputDevice.next();
            if (input.equals("0"))  {  super.hand.show();  }
            else {
                for (int i = 1; i <= super.hand.hand.size(); i++) {
                    // convert index to string to compare to input
                    String index = Integer.toString(i);
                    if (input.equals(index)) {
                        inPlay = super.hand.hand.get(i - 1);
                        confirm = true;
                    }
                }
                if (!confirm) {
                    System.out.println("The card you indicated was not found in your hand");
                }
            }
        }
        // remove the card from the player's hand
        super.hand.removeCard(inPlay);
        return chooseCategory();
    }

    public Card getInPlay() {
        return this.inPlay;
    }

    public int chooseCategory()  {
        // Determine play category from user input
        boolean confirm = false;
        int currentCategoryIndex = -1;
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
            }
        }
        return currentCategoryIndex;
    }
}
