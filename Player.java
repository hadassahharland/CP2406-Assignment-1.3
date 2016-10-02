package src;

/**
 * Created by Hadassah Harland on 21/09/2016.
 * This abstract class parents the AI and User-Controlled Player classes for "Project Mineral: Super Trump"
 */

public abstract class Player  {
    int playerIndex;
    String playerName;
    boolean passed;
    boolean winner;
    //boolean currentPlayer;
    PlayerHand hand;

    public Player(int playerIndex, String playerName) {
        this.playerName = playerName;
        this.playerIndex = playerIndex;
        //this.currentPlayer = false;
        this.winner = false;
        this.hand = new PlayerHand();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setHand(PlayerHand hand)  { this.hand = hand; }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public abstract int newRound();

    public abstract void takeTurn(Card lastCard, int currentCategoryIndex);

    public abstract Card getInPlay();

    public abstract int chooseCategory();

    public abstract boolean magnetiteWinCondition();

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean isWinner() {
        return winner;
    }

    //    public void pass()  {
//        passed = true;
//        System.out.println(this.getPlayerName() + " has passed and picked up a card from the deck");
//        hand.addCard(SuperTrump.currentUser.newGame.dealCard());
//        // TODO fix dealCard null pointer exception
//    }
}