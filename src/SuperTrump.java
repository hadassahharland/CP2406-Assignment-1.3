/**
 * Created by Hadassah Harland on 21/09/2016.
 * This class generates objects and interprets user commands for "Project Mineral: Super Trumps"
 */
public class SuperTrump {
    static User currentUser;
    public static void main(String[] args) {
        currentUser = new User();

    }
    public static void newGame(int noPlayers)  {
        Game newGame = new Game(noPlayers, currentUser);
        newGame.pronounceWinners();
    }

}
