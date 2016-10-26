/**
 * Created by Hadassah on 22/09/2016.
 * This class inherits from the abstract class Player to implement methods using user input.
 */

public class UserControlledPlayer extends Player {
    public Card inPlay;

    public UserControlledPlayer(int playerIndex, String playerName)  {
        super(playerIndex, playerName);
    }

    public void takeTurn(Card lastCard, int currentCategoryIndex) {
        /* User decision: play any card as indicated in hand or pass */
        SuperTrumpGUI.message("The play category is " + Game.categories[currentCategoryIndex]);
        SuperTrumpGUI.message("Choose a card from your hand to play or pass");
        SuperTrumpGUI.window.playerTurn(lastCard, currentCategoryIndex, "Blank");
    }

    public void newRound()  {
        /*User decision: choose a play category and play any card as indicated in hand or pass */
        if (!(SuperTrumpGUI.newGame.lastCard instanceof TrumpCard)) {
            SuperTrumpGUI.message("Choose a play category");
            SuperTrumpGUI.window.playerNewRound("Category");
        }
        SuperTrumpGUI.window.playerNewRound("Blank");
    }

    public Card getInPlay() {
        return this.inPlay;
    }

    public void setInPlay(Card card)  {
        this.inPlay = card;
    }

    public void chooseCategory() {
        SuperTrumpGUI.window.refreshGameGUI("Category");
    }

    public boolean magnetiteWinCondition() {
        /* if the player has the Magnetite card in their hand, ask if they would like to play it and win the game */
        for (int i = 0; i < hand.hand.size(); i++) {
            if (hand.hand.get(i).cardIndex == 45) {                     // the card index of the Magnetite card is 45
                SuperTrumpGUI.message("If the Magnetite card and the Geophysicist card are played together, that player " +
                        "wins the game. You have played the Geophysicist card, and the have the Magnetite card in " +
                        "your hand. Would you like to play the Magnetite card and win the game?");
                return SuperTrumpGUI.yesSelection;
            }
        }
        return false;
    }
}
