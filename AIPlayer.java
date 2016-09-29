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

    public void takeTurn(Card lastCard, int currentCategoryIndex)  {
        // AI decision: play any playcard of a higher value in the current category than the last card played,
        // else play a trump card, else pass
        boolean cardFound = false;
        int i = 0;
        // look for a valid play card to play
        while (!cardFound && i < super.hand.hand.size())  {
            if (super.hand.hand.get(i) instanceof PlayCard)  {
                if (((PlayCard) super.hand.hand.get(i)).validPlay(lastCard, currentCategoryIndex)) {
                    cardFound = true;
                    // put the card into play
                    this.inPlay = super.hand.hand.get(i);
                }
            }
            i++;
        }
        // or look for a trump card to play
        while (!cardFound && i < super.hand.hand.size())  {
            if (super.hand.hand.get(i) instanceof TrumpCard)  {
                cardFound = true;
                // put the card into play
                this.inPlay = super.hand.hand.get(i);
            }
            i++;
        }
        // remove the card from the player's hand
        if (cardFound)  {  super.hand.removeCard(inPlay);  }
        // if no card was found to play, player must pass
        else  {  super.pass(); }
    }

    public int newRound()  {
        // AI decision: Play any play card from hand, return random int corresponding to the currentCategoryIndex
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
        return chooseCategory();
    }

    public Card getInPlay() {
        return inPlay;
    }

    public int chooseCategory()  {
        // AI decision: return random int corresponding to the currentCategoryIndex
        Random rn = new Random();
        int currentCategoryIndex = rn.nextInt(5);
        return currentCategoryIndex;
    }
}

