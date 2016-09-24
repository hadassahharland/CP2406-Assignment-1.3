import java.util.Scanner;

/**
 * Created by Hadassah Harland on 21/09/2016.
 * This class manages user input and user functionalities for the game "Project Mineral: Super Trump"
 */
public class User {
    String userName;
    public User() {
        this.userName = requestUserName();
        showWelcome();
        displayPlayMenu();
        makePlayMenuSelection();
    }

    public void showWelcome()  {
        System.out.println("Hello " + this.userName + ", Welcome to Project Mineral: Super Trumps ");
    }

    public String requestUserName()  {
        System.out.println("Please enter your name ");
        Scanner inputDevice = new Scanner(System.in);
        return inputDevice.next();
    }

    public static void displayPlayMenu()  {
        System.out.println("Play Menu: Please make a selection. \n (1) new game \n (2) quit game " +
                "\n (3) game instructions");
    }

    public static void makePlayMenuSelection() {
        // requests a menu selection from the user and runs the corresponding method
        boolean confirm = false;
        while (!confirm) {
            Scanner inputDevice = new Scanner(System.in);
            int selection = inputDevice.nextInt();
            if (selection == 1) {
                // request confimation from the user
                System.out.println("You have selected (1) new game \n (1) confirm \n (2) return");
                if (inputDevice.nextInt() == 1)  {
                    confirm = true;
                    newGame();
                }
                else  {displayPlayMenu();}
            }
            else if (selection == 2) {
                // request confimation from the user
                System.out.println("You have selected (2) quit game \n (1) confirm \n (2) return");
                if (inputDevice.nextInt() == 1) {
                    confirm = true;
                    quitGame();
                }
                else  {displayPlayMenu();}
            }
            else if (selection == 3) {
                // request confimation from the user
                System.out.println("You have selected (3) game instructions \n (1) confirm \n (2) return");
                if (inputDevice.nextInt() == 1)  {
                    confirm = true;
                    gameInstructions();
                }
                else  {displayPlayMenu();}
            }
            else  {
                System.out.println("Invalid Selection");
                displayPlayMenu();
            }
        }
    }

    public static void newGame()  {
        boolean confirm = false;
        while (!confirm) {
            System.out.println("The game requires 3-5 players. \nPlease enter number of players: ");
            Scanner inputDevice = new Scanner(System.in);
            int noPlayers = inputDevice.nextInt();
            if (!(noPlayers == 3 || noPlayers == 4 || noPlayers == 5)) {
                System.out.println("Invalid selection");
            }
            else  {
                // request confirmation from the user
                System.out.println("You have selected " + noPlayers + " players. \n (1) confirm \n (2) return ");
                if (inputDevice.nextInt() == 1) {
                    confirm = true;
                    // create new game object
                    SuperTrump.newGame(noPlayers);
                }
            }
        }
    }

    public static void quitGame()  {
        System.out.println("Goodbye");
        // end of program
    }

    public static void gameInstructions()  {
        System.out.println(" *** game instructions *** ");
        // Game instructions provided as images
        // Return to play menu
        displayPlayMenu();
        makePlayMenuSelection();
    }

    public String getUserName() {
        return userName;
    }
}
