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
        while (!cardFound && i < hand.hand.size())  {
            if (hand.hand.get(i) instanceof PlayCard)  {
                if ((hand.hand.get(i)).validPlay(lastCard, currentCategoryIndex)) {
                    cardFound = true;
                    // put the card into play
                    this.inPlay = hand.hand.get(i);
                }
            }
            i++;
        }
        // or look for a trump card to play
        while (!cardFound && i < hand.hand.size())  {
            if (hand.hand.get(i) instanceof TrumpCard)  {
                cardFound = true;
                // put the card into play
                this.inPlay = hand.hand.get(i);
            }
            i++;
        }
        // remove the card from the player's hand
        if (cardFound)  {  hand.removeCard(inPlay);  }
        // if no card was found to play, player must pass
        else  {
            this.passed = true;
        }
    }

    public int newRound()  {
        // AI decision: Play any play card from hand, return random int corresponding to the currentCategoryIndex
        boolean cardFound = false;
        int i = 0;
        while (!cardFound && i < hand.hand.size())  {
            if (hand.hand.get(i) instanceof PlayCard)  {
                cardFound = true;
                // put the card into play
                this.inPlay = hand.hand.get(i);
            }
            i++;
        }
        // remove the card from the player's hand
        hand.removeCard(inPlay);
        return chooseCategory();
    }

    public Card getInPlay() {
        return inPlay;
    }

    public int chooseCategory()  {
        // AI decision: return random int corresponding to the currentCategoryIndex
        Random rn = new Random();
        return rn.nextInt(5);
    }

    public boolean magnetiteWinCondition()  {
        // if the player has the Magnetite card in their hand, play it and win the game
        // the card index of the Magnetite card is 45
        for (int i = 0; i < hand.hand.size(); i++)  {
            if (hand.hand.get(i).cardIndex == 45)  {
                hand.winCondition = true;
                return true;
            }
        }
        return false;
    }
}

