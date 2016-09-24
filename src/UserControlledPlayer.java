/**
 * Created by Hadassah on 22/09/2016.
 */
public class UserControlledPlayer extends Player {
    public UserControlledPlayer(int playerIndex, String playerName)  {
        super(playerIndex, playerName);
    }
    public void showHand() {
        super.hand.show();
    }
}
