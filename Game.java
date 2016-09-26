package src;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Hadassah Harland on 21/09/2016.
 * This class initialises and manages the components of a game of "Project Mineral: Super Trump"
 */
public class Game {
    protected int noPlayers;
    public static Player[] players;
    public static PlayerHand[] playerHands;
    int turnNo;
    int currentPlayerIndex;
    Card lastCard;
    Deck deck;

    public Game(int noPlayers) {
        this.turnNo = 0;
        this.noPlayers = noPlayers;
        buildPlayersArray();
        assignDealer(players);
        buildDeck();

        // TODO gameplay

    }


    public void assignDealer(Player[] players) {
        // generate a random integer corresponding to a player index
        Random rn = new Random();
        int dealerIndex = rn.nextInt(players.length - 1);
        System.out.println("The dealer is " + players[dealerIndex].getPlayerName());
        this.currentPlayerIndex = dealerIndex;

    }

    public void buildPlayersArray() {
        players = new Player[noPlayers];
        int playerIndex;
        players[0] = buildPlayers(0, true);               // first player is user
        for (playerIndex = 1; playerIndex < noPlayers; playerIndex++) {
            players[playerIndex] = buildPlayers(playerIndex, false);
        }
    }

    public Player buildPlayers(int playerIndex, boolean isUser) {
        System.out.println("Please enter player" + (playerIndex + 1) + "'s name, or (1) to assign the computer " +
                "default");
        Scanner inputDevice = new Scanner(System.in);
        String userInput = inputDevice.next();
        String playerName;
        if (userInput.equals("1")) {
            playerName = "Player" + (playerIndex + 1);
        }
        else {
            playerName = userInput;
        }
        if (!isUser) {
            return new AIPlayer(playerIndex, playerName);
        } else {
            return new UserControlledPlayer(playerIndex, playerName);
        }
    }

    public void buildDeck() {
        Deck deck = new Deck();
    }
    public void deal()  {
        playerHands = new PlayerHand[noPlayers];

        for (int cardsDealt = 0; cardsDealt < 8; cardsDealt++)  {
            for (int playerDealt = 0; playerDealt < noPlayers; playerDealt++)  {

            }
        }
    }

//    public void pronounceWinners()  {
//        System.out.println("***the winners***");
//    }
//
//    public void newRound() {
//
//    }
//
//    public void newTurn(Player[] players) {
//        System.out.println("It is now " + players[currentPlayerIndex].getPlayerName() + "'s turn ");
//        System.out.println("The last card played was: \n" + lastCard.toString());
//        players[currentPlayerIndex].takeTurn(lastCard);
//        // TODO prompt player to take turn
//
//    }
}


