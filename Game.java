package src;

import com.sun.org.apache.xpath.internal.SourceTree;

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
    int currentCategoryIndex;
    String[] winners;
    Card inPlay;
    Card lastCard;
    int playersOut;
    int roundNo;
    Deck deck;

    public Game(int noPlayers) {
        startGame(noPlayers);
        playGame();
        endGame();
    }

    public void startGame(int noPlayers)  {
        this.turnNo = 0;
        this.noPlayers = noPlayers;
        this.winners = new String[noPlayers];
        this.playersOut = 0;
        this.roundNo = 0;
        buildPlayersArray();
        assignDealer(players);
        buildDeck();
        deal();
    }

    public void playGame()  {
        newRound();
        //TODO take turns
    }

    public void endGame()  {
        System.out.println("The game has ended.");
        pronounceWinners();
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
        this.deck = new Deck();
    }

    public void deal()  {
        playerHands = new PlayerHand[noPlayers];
        // the first card is dealt to the first PlayerHand in the playerHands ArrayList.
        for (int cardsDealt = 0; cardsDealt < 8; cardsDealt++)  {
            for (int playerDealt = 0; playerDealt < noPlayers; playerDealt++)  {
                // initialize PlayerHands with first card
                if (cardsDealt == 0)  {
                    playerHands[playerDealt] = new PlayerHand();
                }
                // add the first card in the playDeck to the player's hand
                playerHands[playerDealt].addCard(deck.playDeck.get(0));
                // remove this card from the playDeck
                deck.playDeck.remove(0);
            }
        }
        // set PlayerHands to each Player's this.hand
        for (int i = 0; i < noPlayers; i++)  {
            players[i].setHand(playerHands[i]);
        }
        // dealing the cards is the dealer's first turn
        nextCurrentPlayer();
    }

    public void pronounceWinners()  {
        String winnerStatement = "The winner is " + this.winners[0];
        String loserStatement = ", and the loser is " + this.winners[noPlayers-1];
        String runnerUpStatement;
        if (noPlayers == 3)  {
            runnerUpStatement = ", the runner up is " + this.winners[1];
        }
        else if  (noPlayers == 4)  {
            runnerUpStatement = ", the runner ups are " + this.winners[1] + " and " + this.winners[2];
        }
        else  {
            runnerUpStatement = ", the runner ups are " + this.winners[1] + ", " + this.winners[2] + " and "
                    + this.winners[3];
        }
        System.out.println(winnerStatement + runnerUpStatement + loserStatement);
    }

    public void nextCurrentPlayer()  {  currentPlayerIndex = ((currentPlayerIndex + 1) % noPlayers);  }

    public void newRound() {
        roundNo++;
        // declare new round
        System.out.println("New round: Round " + roundNo);
        System.out.println("It is " + players[currentPlayerIndex].getPlayerName() + "'s turn to start the round. ");
        this.currentCategoryIndex = players[currentPlayerIndex].newRound();
        playCard(players[currentPlayerIndex].getInPlay());
    }

    public void playCard(Card card)  {
        // assign card to class variable lastCard
        this.lastCard = card;
        // declare card as played
        String currentCategory;
        if (currentCategoryIndex == 0)  {  currentCategory = "hardness";  }
        else if (currentCategoryIndex == 1)  {  currentCategory = "specific gravity";  }
        else if (currentCategoryIndex == 2)  {  currentCategory = "cleavage";  }
        else if (currentCategoryIndex == 3)  {  currentCategory = "crystal abundance";  }
        else  {  currentCategory = "economic value";  }
        System.out.println("The current category for comparison is " + currentCategory + ". \n"
                + players[currentCategoryIndex].getPlayerName() + " has played the following card: \n"
                + card.toString());
        checkWinners();
    }

    public void checkWinners()  {
        for (int i = 0; i < noPlayers; i++)  {
            if (players[i].hand.winCondition)  {
                // add to winners
                winners[playersOut] = players[i].getPlayerName();
                playersOut++;
                if (playersOut == (noPlayers-1))  {
                    // TODO set loser
                    endGame();
                }
            }
        }
    }
//
//    public void newTurn(Player[] players) {
//        System.out.println("It is now " + players[currentPlayerIndex].getPlayerName() + "'s turn ");
//        System.out.println("The last card played was: \n" + lastCard.toString());
//        players[currentPlayerIndex].takeTurn(lastCard);
//        // TODO prompt player to take turn
//
//    }
}


