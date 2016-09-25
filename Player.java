package src;

/**
 * Created by Hadassah Harland on 21/09/2016.
 * This abstract class parents the AI and User-Controlled Player classes for "Project Mineral: Super Trump"
 */

public abstract class Player  {
    int playerIndex;
    String playerName;
    boolean isDealer;
    boolean currentPlayer;
    PlayerHand hand;
    public Player(int playerIndex, String playerName) {
        this.playerName = playerName;
        this.playerIndex = playerIndex;
        this.isDealer = false;
        this.currentPlayer = false;
        hand = new PlayerHand();
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(byte playerIndex) {
        this.playerIndex = playerIndex;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public void setDealer(boolean dealer) {
        isDealer = dealer;
    }

    public abstract void takeTurn(Card lastCard);

        // TODO acquire cards


    // TODO view cards
    // TODO play card + state card details
    // TODO pass turn
    // TODO win condition
    // TODO view last card

}