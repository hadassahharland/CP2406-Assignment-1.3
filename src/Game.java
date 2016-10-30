import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Hadassah Harland on 21/09/2016.
 * This class initialises and manages the components of a game of "Project Mineral: Super Trump"
 */
public class Game {

    static String[] categories = {"hardness", "specific gravity", "cleavage", "crustal abundance", "economic value"};
    public Deck deck;
    public static Player[] players;
    public static int currentPlayerIndex;
    public static int currentCategoryIndex;
    public Card lastCard;
    private String[] winners;
    private String[] playerNames;
    private int playersOut;
    private int roundNo;
    private int noPlayers;
    private ArrayList<Card> discardPile;
    private boolean endOfGame;

    public Game(String[] playerNames) {
        startGame(playerNames);
    }

    public void startGame(String[] playerNames)  {
        this.noPlayers = playerNames.length;
        this.playerNames = playerNames;
        this.winners = new String[noPlayers];
        this.playersOut = 0;
        this.endOfGame = false;
        this.roundNo = 0;
        discardPile = new ArrayList<>();
        buildPlayersArray();
        assignDealer(players);
        this.deck = new Deck();
        deal();
    }

    public void playGame()  {
        newRound();
    }

    public void endGame()  {
        endOfGame = true;
        SuperTrumpGUI.message("The game has ended.");
        String winStatement;
        if (deck.playDeck.isEmpty())  {
            winStatement = "The game ended as we have run out of cards";
        } else {
            winStatement = pronounceWinners();
        }
        SuperTrumpGUI.window.endGame(winStatement);


    }

    public static void setCurrentCategoryIndex(int index) {
        currentCategoryIndex = index;
    }

    public void assignDealer(Player[] players) {
        // generate a random integer corresponding to a player index
        Random rn = new Random();
        int dealerIndex = rn.nextInt(players.length);
        SuperTrumpGUI.message("The dealer is " + players[dealerIndex].getPlayerName());
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
        String playerName = playerNames[playerIndex];
        if (!isUser) {
            return new AIPlayer(playerIndex, playerName);
        } else {
            return new UserControlledPlayer(playerIndex, playerName);
        }
    }

    public void deal()  {
        PlayerHand[] playerHands = new PlayerHand[players.length];
        // the first card is dealt to the first PlayerHand in the playerHands ArrayList.
        for (int cardsDealt = 0; cardsDealt < 8; cardsDealt++)  {
            for (int playerDealt = 0; playerDealt < players.length; playerDealt++)  {
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
        for (int i = 0; i < players.length; i++)  {
            players[i].setHand(playerHands[i]);
        }
        // dealing the cards is the dealer's first turn
        nextCurrentPlayer();
    }

    public PlayerHand getPlayerHand(int playerIndex)  {
        return players[playerIndex].hand;
    }

    public static Player[] getPlayers() {
        return players;
    }

    public Player getPlayer(int playerIndex)  {
        return players[playerIndex];
    }

    public String pronounceWinners()  {
        String winnerStatement = "The winner is " + winners[0];
        String loserStatement = ", and the loser is " + winners[players.length-1];
        String runnerUpStatement;
        if (players.length == 3)  {
            runnerUpStatement = ", the runner up is " + winners[1];
        } else if  (players.length == 4)  {
            runnerUpStatement = ", the runner ups are " + winners[1] + " and " + winners[2];
        } else  {
            runnerUpStatement = ", the runner ups are " + winners[1] + ", " + winners[2] + " and "
                    + winners[3];
        }
        SuperTrumpGUI.message(winnerStatement + runnerUpStatement + loserStatement);
        return winnerStatement + runnerUpStatement + loserStatement;
    }

    public void nextCurrentPlayer()  {
        currentPlayerIndex = ((currentPlayerIndex + 1) % players.length);
        // if the current player is
        while ((players[currentPlayerIndex].isPassed()) || (players[currentPlayerIndex].isWinner()))  {
            if (players[currentPlayerIndex].isPassed()) {
                SuperTrumpGUI.message(players[currentPlayerIndex].getPlayerName() + " has passed until the end of the round.");
            }
            currentPlayerIndex = ((currentPlayerIndex + 1)% players.length);
        }
    }

    public void newRound() {
        roundNo++;
        // declare new round
        SuperTrumpGUI.message("New round: Round " + roundNo);
        // set all players passed status to false
        for (int i = 0; i < players.length; i++) {
            players[i].setPassed(false);
        }
        SuperTrumpGUI.message("It is " + players[currentPlayerIndex].getPlayerName() + "'s turn to start the round. ");
        players[currentPlayerIndex].newRound();
    }

    public void newRoundEnd()  {
        SuperTrumpGUI.message(players[currentPlayerIndex].getPlayerName() + " has chosen the play category to be "
                + categories[currentCategoryIndex]);
        playCard(players[currentPlayerIndex].getInPlay());
        endOfTurn();
    }

    public void playCard(Card card) {
        // add card to discard pile
        discardPile.add(card);
        if (card instanceof PlayCard) {
            playCard((PlayCard) card);
        } else if (card instanceof TrumpCard) {
            playCard((TrumpCard) card);
        }
    }

    public Deck getDeck() {
        return deck;
    }

    public void playCard(PlayCard card)  {
        // assign card to class variable lastCard
        this.lastCard = card;
        // declare card as played
        String currentCategory;
        String categoryValue;
        if (currentCategoryIndex == 0)  {
            currentCategory = "hardness";
            categoryValue = card.hardness;
        } else if (currentCategoryIndex == 1)  {
            currentCategory = "specific gravity";
            categoryValue = card.specificGravity;
        } else if (currentCategoryIndex == 2)  {
            currentCategory = "cleavage";
            categoryValue = card.cleavage;
        } else if (currentCategoryIndex == 3)  {
            currentCategory = "crustal abundance";
            categoryValue = card.crustalAbundance;
        } else  {
            currentCategory = "economic value";
            categoryValue = card.economicValue;
        }
        SuperTrumpGUI.message(players[currentPlayerIndex].getPlayerName() + " has played a card: \n" + card.name + ", "
                + currentCategory + ", " + categoryValue);
    }

    public void playCard(TrumpCard card)  {
        // determine the new play category
        if (card.cardIndex == 54)  {                                    // the Miner: economic value
            currentCategoryIndex = 4;
        } else if (card.cardIndex == 55)  {                             // the Petrologist: crustal abundance
            currentCategoryIndex = 3;
        } else if (card.cardIndex == 56)  {                             // the Gemmologist: hardness
            currentCategoryIndex = 0;
        } else if (card.cardIndex == 57)  {                             // the Mineralogist: cleavage
            currentCategoryIndex = 2;
        } else if (card.cardIndex == 58)  {                             // the Geophysicist: specific gravity
            currentCategoryIndex = 1;
            /* If a player throws the Geophysicist card together with the Magnetite card, then that player wins */
            if (players[currentPlayerIndex].magnetiteWinCondition())  {
                SuperTrumpGUI.message(players[currentPlayerIndex].getPlayerName() + " has played the Geophysicist card " +
                        "and the Magnetite card and so has won.");
                checkWinners();
                if (!endOfGame)  {
                    newRound();
                }
            }
        } else if (card.cardIndex == 59)  {                             // the Geologist: Player to choose category
            currentCategoryIndex = players[currentPlayerIndex].categoryChoice;
        } else {
            SuperTrumpGUI.message("An error has occurred, this is not a Trump Card");
        }
        // declare new round
        roundNo++;
        SuperTrumpGUI.message("A trump card was played! \nNew round: Round " + roundNo);
        // set all players passed status to false
        for (int i = 0; i < players.length; i++)  {  players[i].setPassed(false); }
        SuperTrumpGUI.message(players[currentPlayerIndex].getPlayerName() + " has played a card: \n"
                + card.toString() + "\nThe play category is now " + categories[currentCategoryIndex]);
        this.lastCard = card;
    }

    public void checkWinners()  {
        for (int i = 0; i < players.length; i++)  {
            // if the win condition is true, and the winner not already set, set winner
            if ((players[i].hand.winCondition) && (!players[i].isWinner()))  {
                // add to winners
                winners[playersOut] = players[i].getPlayerName();
                SuperTrumpGUI.message(players[i].getPlayerName() + " has won");
                players[i].setWinner(true);
                playersOut++;
                noPlayers--;
                if (noPlayers == 1)  {                                  // last player is the loser
                    for (int j = 0; j < players.length; j++) {
                        if (!players[j].hand.winCondition) {            // identify last player
                            winners[winners.length - 1] = players[j].getPlayerName();
                            endOfGame = true;
                            endGame();
                        }
                    }

                }
            }
        }
    }

    public boolean checkPassed()  {
        // determine how many players have not passed
        int playersIn = players.length;
        Player nextPlayer = null;
        for (int i = 0; i < players.length; i++)  {
            if (players[i].isPassed() || players[i].isWinner())  {
                playersIn--;
            } else  { nextPlayer = players[i]; }
        }
        if (playersIn == 1) {
            /* New round is started by the player who had not passed */
            currentPlayerIndex = nextPlayer.playerIndex;
            if (!endOfGame) {
                //newRound();
                return true;
            }
        }
        return false;
    }

    public void endOfTurn()  {
        checkWinners();
        if (!checkPassed() && !endOfGame)  {
            nextCurrentPlayer();
            newTurn();
        } else if (checkPassed())  {
            newRound();
        }
    }

    public void newTurn() {
        SuperTrumpGUI.message("It is now " + players[currentPlayerIndex].getPlayerName() + "'s turn ");
        players[currentPlayerIndex].takeTurn(lastCard, currentCategoryIndex);
    }

    public void newTurnEnd()  {
        /* if the player has passed on this turn the boolean is true */
        if (players[currentPlayerIndex].isPassed()) {
            SuperTrumpGUI.message(players[currentPlayerIndex].getPlayerName() + " has passed and picked up a card from " +
                    "the deck");
            checkDeck();
            Card card = deck.playDeck.get(0);                       // take the first card in the deck
            deck.playDeck.remove(card);                             // remove that card from the deck
            checkDeck();
            players[currentPlayerIndex].hand.addCard(card);         // add it to the player's hand
        } else  {
            playCard(players[currentPlayerIndex].getInPlay());
        }
        endOfTurn();
    }

    public void checkDeck()  {
        /* if the deck is empty, shuffle the discard pile into the deck */
        if (deck.playDeck.isEmpty())  {
            SuperTrumpGUI.message("The Deck has been reshuffled");
            deck.playDeck = discardPile;
            Collections.shuffle(deck.playDeck);
            if (deck.playDeck.isEmpty())  {
                SuperTrumpGUI.message("No more Cards");
                endGame();
            }
        }
    }
}


