/**
 * Created by Hadassah Harland on 21/09/2016.
 * This abstract class parents the AI and User-Controlled Player classes for "Project Mineral: Super Trump"
 */

public abstract class Player {
    public int playerIndex;
    public int categoryChoice;
    public String playerName;
    public boolean passed;
    public boolean winner;
    public PlayerHand hand;

    public Player(int playerIndex, String playerName) {
        this.playerName = playerName;
        this.playerIndex = playerIndex;
        this.winner = false;
        this.hand = new PlayerHand();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setHand(PlayerHand hand) {
        this.hand = hand;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public abstract void newRound();

    public abstract void takeTurn(Card lastCard, int currentCategoryIndex);

    public abstract Card getInPlay();

    public abstract void setInPlay(Card card);

    public abstract void chooseCategory();

    public abstract boolean magnetiteWinCondition();

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean isWinner() {
        return winner;
    }

}