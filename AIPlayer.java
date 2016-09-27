package src;

import java.util.Random;

/**
 * Created by Hadassah on 22/09/2016.
 * This class inherits from the abstract class Player to implement methods using computer logic.
 */
public class AIPlayer extends Player  {
    Card inPlay;

    public AIPlayer(int playerIndex, String playerName)  {
        super(playerIndex, playerName);
    }

    public void takeTurn(Card lastCard)  {
        System.out.println("***take turn***");
    }

    public int newRound()  {
        // AI decision: Play any play card from hand, return random int corresponding to the currentCategoryIndex
        Random rn = new Random();
        int currentCategoryIndex = rn.nextInt(4);
        boolean cardFound = false;
        int i = 0;
        while (!cardFound && i < super.hand.hand.size())  {
            if (super.hand.hand.get(i) instanceof PlayCard)  {
                cardFound = true;
                // put the card into play
                this.inPlay = super.hand.hand.get(i);
            }
            i++;
        }
        // remove the card from the player's hand
        super.hand.removeCard(inPlay);
        return currentCategoryIndex;
    }

    public Card getInPlay() {
        return inPlay;
    }
}

