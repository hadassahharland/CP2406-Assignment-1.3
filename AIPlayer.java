package src;

/**
 * Created by Hadassah on 22/09/2016.
 * This class inherits from the abstract class Player to implement methods using computer logic.
 */
public class AIPlayer extends Player  {

    public AIPlayer(int playerIndex, String playerName)  {
        super(playerIndex, playerName);

    }

    public void takeTurn(Card lastCard)  {
        System.out.println("***take turn***");
    }
}
