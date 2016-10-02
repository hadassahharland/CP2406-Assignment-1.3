package src;

import java.util.Scanner;

/**
 * Created by Hadassah Harland on 21/09/2016.
 * This class manages user input and user functionalities for the game "Project Mineral: Super Trump"
 */
public class User {
    Game newGame;
    //String userName;
    public User() {
        showWelcome();
        displayPlayMenu();
        makePlayMenuSelection();
    }

    public void showWelcome()  {
        System.out.println("Hello, Welcome to Project Mineral: Super Trumps ");
    }

    public void displayPlayMenu()  {
        System.out.println("Play Menu: Please make a selection. \n (1) new game \n (2) quit game " +
                "\n (3) game instructions");
    }

    public void makePlayMenuSelection() {
        // requests a menu selection from the user and runs the corresponding method
        boolean confirm = false;
        while (!confirm) {
            Scanner inputDevice = new Scanner(System.in);
            String selection = inputDevice.next();
            if (selection.equals("1")) {
                // request confimation from the user
                System.out.println("You have selected (1) new game \n (1) confirm \n (2) return");
                if (inputDevice.next().equals("1"))  {
                    confirm = true;
                    newGame();
                    // at the end of the game, return to play menu
                    displayPlayMenu();
                    makePlayMenuSelection();
                }
                else  {displayPlayMenu();}
            }
            else if (selection.equals("2")) {
                // request confimation from the user
                System.out.println("You have selected (2) quit game \n (1) confirm \n (2) return");
                if (inputDevice.next().equals("1")) {
                    confirm = true;
                    quitGame();
                }
                // invalid selection will also return
                else  {displayPlayMenu();}
            }
            else if (selection.equals("3")) {
                // request confimation from the user
                System.out.println("You have selected (3) game instructions \n (1) confirm \n (2) return");
                if (inputDevice.next().equals("1"))  {
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

    public void newGame()  {
        boolean confirm = false;
        while (!confirm) {
            System.out.println("The game requires 3-5 players. \nPlease enter number of players: ");
            Scanner inputDevice = new Scanner(System.in);
            String noPlayersString = inputDevice.next();
            if (!(noPlayersString.equals("3") || noPlayersString.equals("4") || noPlayersString.equals("5"))) {
                System.out.println("Invalid selection");
            }
            else  {
                // convert string to int (code only accessable if string is the number 3, 4 or 5)
                int noPlayers = Integer.parseInt(noPlayersString);
                // request confirmation from the user
                System.out.println("You have selected " + noPlayers + " players. \n (1) confirm \n (2) return ");
                if (inputDevice.next().equals("1")) {
                    confirm = true;
                    // create new game object
                    this.newGame = new Game(noPlayers);
                }
            }
        }
    }

    public void quitGame()  {
        System.out.println("Goodbye");
        // end of program
    }

    public void gameInstructions()  {
        System.out.println(" *** game instructions *** ");
        // Game instructions provided as images
        // Return to play menu
        displayPlayMenu();
        makePlayMenuSelection();
    }

}
