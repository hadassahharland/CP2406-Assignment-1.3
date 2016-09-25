package src;

/**
 * Created by Hadassah on 22/09/2016.
 * This class inherits from the abstract class Player to implement methods using user input.
 */
public class UserControlledPlayer extends Player {
    public UserControlledPlayer(int playerIndex, String playerName)  {
        super(playerIndex, playerName);
    }

    public void showHand() {
        super.hand.show();
    }

    public void takeTurn(Card lastCard) {
        System.out.println("***take turn***");

    }
}
