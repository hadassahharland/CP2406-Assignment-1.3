import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

/**
 * Created by Hadassah on 22/09/2016.
 * This class inherits from the abstract class Player to implement methods using user input.
 */

public class UserControlledPlayer extends Player {
    public Card inPlay;

    public UserControlledPlayer(int playerIndex, String playerName)  {
        super(playerIndex, playerName);
    }

//    public void showHand() {
//        super.hand.show();
//    }

    public void takeTurn(Card lastCard, int currentCategoryIndex) {
        /* User decision: play any card as indicated in hand or pass */
        //System.out.println("The last card played was: \n" + lastCard.toString());
        SuperTrumpGUI.Message("The play category is " + Game.categories[currentCategoryIndex]);
        SuperTrumpGUI.Message("Choose a card from your hand to play or pass");

        //showHand();
//        boolean confirm = false;
//        boolean cardPlayed = false;
//        boolean endTurn = false;
//        while (!endTurn)  {
//            SuperTrumpGUI.Message("Choose a card from your hand to play or pass");
//            Scanner inputDevice = new Scanner(System.in);
//            String input = inputDevice.next();
//            if (input.equals("0"))  {
//                hand.show();
//                confirm = true;
//            } else if (input.equals("00"))  {
//                confirm = true;
//                endTurn = true;
//                this.passed = true;
//            } else {
//                for (int i = 1; i <= super.hand.hand.size(); i++) {
//                    // convert index to string to compare to input
//                    String index = Integer.toString(i);
//                    if (input.equals(index)) {
//                        Card card = super.hand.hand.get(i - 1);
//                        confirm = true;
//                        if (card.validPlay(lastCard, currentCategoryIndex)) {
//                            inPlay = super.hand.hand.get(i - 1);
//                            cardPlayed = true;
//                            endTurn = true;
//                        } else {
//                            /* occurs if the card selected is a play card that is not higher in the play category */
//                            System.out.println("The card you selected is not a valid play");
//                        }
//                    }
//                }
//            }
//            if (!confirm) {
//                System.out.println("The card you selected was not found in your hand");
//            }
//        }
//        // remove the card from the player's hand
//        if (cardPlayed)  {  super.hand.removeCard(inPlay); }
    }

    public int newRound()  {
        // User decision: By input.
        //showHand();
//        boolean confirm = false;
//        boolean endTurn = false;
//        while (!endTurn)  {
//            System.out.println("Choose a card from your hand to play, or (0) to show hand");
//            Scanner inputDevice = new Scanner(System.in);
//            String input = inputDevice.next();
//            if (input.equals("0"))  {
//                hand.show();
//                confirm = true;
//            } else {
//                for (int i = 1; i <= super.hand.hand.size(); i++) {
//                    // convert index to string to compare to input
//                    String index = Integer.toString(i);
//                    if (input.equals(index)) {
//                        inPlay = super.hand.hand.get(i - 1);
//                        confirm = true;
//                        endTurn = true;
//                    }
//                }
//                if (!confirm) {
//                    System.out.println("The card you indicated was not found in your hand");
//                }
//            }
//        }
//        // remove the card from the player's hand
//        hand.removeCard(inPlay);
        return chooseCategory();
    }

    public Card getInPlay() {
        return this.inPlay;
    }

    public void setInPlay(Card card)  {
        this.inPlay = card;
    }

    public int chooseCategory() {
        return 2;
    }
//        // Determine play category from user input
//        boolean confirm = false;
//        int currentCategoryIndex = -1;
//        while (!confirm) {
//            System.out.println("Choose a play category to start: \n (1) Hardness \n (2) Specific Gravity \n (3) " +
//                    "Cleavage \n (4) Crystal Abundance \n (5) Economic Value");
//            Scanner inputDevice = new Scanner(System.in);
//            String input = inputDevice.next();
//            if (input.equals("1")) {
//                currentCategoryIndex = 0;
//                confirm = true;
//            } else if (input.equals("2")) {
//                currentCategoryIndex = 1;
//                confirm = true;
//            } else if (input.equals("3")) {
//                currentCategoryIndex = 2;
//                confirm = true;
//            } else if (input.equals("4")) {
//                currentCategoryIndex = 3;
//                confirm = true;
//            } else if (input.equals("5")) {
//                currentCategoryIndex = 4;
//                confirm = true;
//            } else {
//                System.out.println("Invalid input");
//            }
//        }
        //return currentCategoryIndex;
    //}

    public boolean magnetiteWinCondition() {
        /* if the player has the Magnetite card in their hand, ask if they would like to play it and win the game */
        for (int i = 0; i < hand.hand.size(); i++) {
            if (hand.hand.get(i).cardIndex == 45) {                     // the card index of the Magnetite card is 45
                SuperTrumpGUI.Message("If the Magnetite card and the Geophysicist card are played together, that player " +
                        "wins the game. You have played the Geophysicist card, and the have the Magnetite card in " +
                        "your hand. Would you like to play the Magnetite card and win the game?");
                return SuperTrumpGUI.yesSelection;
            }
        }
        return false;
    }
}
