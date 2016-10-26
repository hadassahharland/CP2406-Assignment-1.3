import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Hadassah on 21/10/2016.
 * This class manages the GUI for the Project Mineral Super Trumps Game
 */
public class SuperTrumpGUI extends JFrame implements ActionListener, MouseListener {
    public static SuperTrumpGUI window;
    public static Game newGame;
    public static boolean yesSelection, turnHad, selected, newRound;
    public Card lastCard;
    public Card inPlay;
    public int currentCategoryIndex;
    private static JButton start, quit, instruct, returnToMenu, nextImage, playersContinue, startGame, backToNoSelect;
    private static JButton tempMenu, pass, viewHand, homeMenu, inGameInstruct, backtoGame, yesChoice, noChoice, select;
    private static JLabel instructImage, player2window, player3window, player4window, player5window, lastCardView;
    private static JPanel dialogue;
    private static JComboBox<Integer> noPlayersChoice;
    private static JComboBox<String> chooseCategory;
    private static JTextField player1Name, player2Name, player3Name, player4Name, player5Name;
    private String[] playerNames;
    private boolean isInGame;
    private static final int DIALOGUE_NO = 18;
    private static JLabel[] cardImages;
    private int instructImageIndex, categorySelection;
    private ArrayList<JLabel> playerHandView;
    private static ArrayList<String> messages;
    private static final Dimension DIM = new Dimension(1400,1000);
    private static final Font HEADING = new Font("Century",Font.BOLD, 22);
    private static final Font BUTTONS = HEADING.deriveFont(12);
    private static final Font LABELS = HEADING.deriveFont(Font.PLAIN,12);
    private Container con = getContentPane();
    // TODO better way to do Instruct Images
    private String[] instructImageDetail = {"Slide61.jpg","Slide62.jpg","Slide63.jpg","Slide64.jpg"};

    public static void main(String[] args) {
        window = new SuperTrumpGUI();
    }

    private SuperTrumpGUI()  {
        super("Project Mineral: Super Trumps");
        window = this;
        setSize(DIM);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        // Some standard buttons
        returnToMenu = new JButton("Return to Home menu");
        returnToMenu.setFont(BUTTONS);
        returnToMenu.addActionListener(this);
        quit = new JButton("Quit");
        quit.setFont(BUTTONS);
        quit.addActionListener(this);
        instruct = new JButton("View Instructions");
        instruct.setFont(BUTTONS);
        instruct.addActionListener(this);
        backtoGame = new JButton("Return to Game");
        backtoGame.setFont(BUTTONS);
        backtoGame.addActionListener(this);
        yesChoice = new JButton("Yes");
        yesChoice.setFont(BUTTONS);
        yesChoice.addActionListener(this);
        noChoice = new JButton("No");
        noChoice.setFont(BUTTONS);
        noChoice.addActionListener(this);
        select = new JButton("Select");
        select.setFont(BUTTONS);
        select.addActionListener(this);

        messages = new ArrayList<>();
        messages.add("Welcome To Project Mineral: Super Trumps");


        // Call menu
        menu();
    }

    private void menu()  {
        con.removeAll();
        con.setLayout(new GridLayout(4,1,20,20));

        // Welcome Label
        JLabel welcome = new JLabel("Welcome to Project Mineral: Super Trumps!");
        welcome.setFont(HEADING);
        welcome.setHorizontalAlignment(JLabel.CENTER);
        con.add(welcome);

        // Start Button
        start = new JButton("Start Game");
        start.setFont(BUTTONS);
        start.addActionListener(this);
        con.add(start);

        // View Instructions Button
        con.add(instruct);

        // Quit Button
        con.add(quit);

        invalidate();
        validate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == start) {
            retrieveNoPlayers();
        }
        if (source == instruct)  {
            viewInstructions();
        }
        if (source == quit)  {
            dispose();
        }
        if (source == returnToMenu)  {
            menu();
        }
        if (source == nextImage)  {
            refreshInstructions();
        }
        if (source == playersContinue)  {
            retrievePlayerNames();
        }
        if (source == backToNoSelect)  {
            retrieveNoPlayers();
        }
        if (source == startGame)  {
            savePlayerNames();
            isInGame = true;
            createGameGUI();
        }
        if (source == tempMenu)  {
            tempMenu();
        }
        if (source == pass)  {
            message(newGame.players[0].getPlayerName() + " has passed and picked up a card.");
            newGame.players[0].setPassed(true);
            turnHad = true;
            if (newRound && selected)  {
                continueNewRound();
            } else if (!newRound)  {
                continueNewTurn();
            }
        }
        if (source == viewHand)  {
            viewHand();
        }
        if (source == backtoGame)  {
            refreshGameGUI("Blank");
        }
        if (source == yesChoice)  {
            yesSelection = true;
        }
        if (source == noChoice)  {
            yesSelection = false;
        }
        if (source == select)  {
            message("You have chosen the play category to be " + chooseCategory.getSelectedItem());
            Game.setCurrentCategoryIndex(chooseCategory.getSelectedIndex());
            newGame.players[0].categoryChoice = chooseCategory.getSelectedIndex();
            selected = true;
            System.out.println("Selected!");
            if (turnHad)  {
                continueNewRound();
            } else {
                refreshGameGUI("Category");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        for (int i = 0; i < playerHandView.size(); i++)  {
            if (source == playerHandView.get(i))  {
                turnHad = true;
                if (newGame.getPlayerHand(0).hand.get(i).validPlay(lastCard,currentCategoryIndex)) {
                    inPlay = newGame.getPlayerHand(0).hand.get(i);
                    newGame.getPlayerHand(0).hand.remove(i);
                    newGame.players[0].setInPlay(inPlay);
                    refreshGameGUI("Blank");
                    if (newRound)  {
                        continueNewRound();
                    } else {
                        continueNewTurn();
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void createGameGUI() {
        con.removeAll();
        con.setLayout(new GridLayout(3, 4, 5, 5));

        newGame = new Game(playerNames);
        loadCardImages();

        // Display Player Hand details
        player2window = new JLabel(playerNames[1] + " has " + newGame.getPlayerHand(1).hand.size() + " cards");
        player2window.setFont(LABELS);
        con.add(player2window);
        player3window = new JLabel(playerNames[2] + " has " + newGame.getPlayerHand(2).hand.size() + " cards");
        player3window.setFont(LABELS);
        con.add(player3window);
        if (playerNames.length > 3)  {
            player4window = new JLabel(playerNames[3] + " has " + newGame.getPlayerHand(3).hand.size() + " cards");
            player4window.setFont(LABELS);
            con.add(player4window);
            if (playerNames.length > 4)  {
                player5window = new JLabel(playerNames[4] + " has " + newGame.getPlayerHand(4).hand.size() + " cards");
                player5window.setFont(LABELS);
                con.add(player5window);
            }
        }

        // add appropriate number of blank labels to fill the grid
        for (int i = playerNames.length-1; i < 6; i++) {
            con.add(new JLabel());
        }

        // Last Card Label
        lastCardView = new JLabel("Last Card");
        con.add(lastCardView);

        // Dialogue Box View
        dialogue = new JPanel(new GridLayout(DIALOGUE_NO,1));
        con.add(dialogue);
        for (int i = 0; i < messages.size(); i++)  {
            JLabel message = new JLabel(messages.get(i));
            message.setFont(LABELS);
            dialogue.add(message);
        }

        // View Hand Button
        viewHand = new JButton("View Hand");
        viewHand.setFont(BUTTONS);
        viewHand.addActionListener(this);
        con.add(viewHand);

        // Pass button
        pass = new JButton("Pass");
        pass.setFont(BUTTONS);
        pass.addActionListener(this);
        con.add(pass);

        con.add(new JLabel());                  // blank Label

        // Temp menu Button
        tempMenu = new JButton("menu");
        tempMenu.setFont(BUTTONS);
        tempMenu.addActionListener(this);
        con.add(tempMenu);

        invalidate();
        validate();
        repaint();

        newGame.playGame();
    }

    private void retrieveNoPlayers()  {
        con.removeAll();
        con.setLayout(new GridLayout(5,0));

        // Number of Players prompt
        JLabel playerNoPrompt = new JLabel("Select number of players from the list");
        playerNoPrompt.setFont(HEADING);
        con.add(playerNoPrompt);

        // Drop-Down-List
        Integer[] numbersArray = {3, 4, 5};
        noPlayersChoice = new JComboBox<>(numbersArray);
        con.add(noPlayersChoice);

        con.add(new JLabel());              // Blank Label

        // Continue Button
        playersContinue = new JButton("Continue");
        playersContinue.setFont(BUTTONS);
        playersContinue.addActionListener(this);
        con.add(playersContinue);

        // Back to menu
        con.add(returnToMenu);

        invalidate();
        validate();
        repaint();
    }

    private void viewInstructions()  {
        con.removeAll();
        window.setResizable(false);
        instructImageIndex = 0;
        con.setLayout(new BorderLayout());

        // Return To menu Button
        if (isInGame)  {
            tempMenu.setText("Return to menu");
            con.add(tempMenu, BorderLayout.WEST);
        } else {
            con.add(returnToMenu, BorderLayout.WEST);
        }

        // Retrieve Instruction Image from file and scale to Window
        ImageIcon icon = new ImageIcon("Images\\" + instructImageDetail[instructImageIndex]);
        instructImage = new JLabel(scale(icon, 600, 700));
        con.add(instructImage, BorderLayout.CENTER);

        // Next Button
        nextImage = new JButton("Next");
        nextImage.addActionListener(this);
        nextImage.setFont(BUTTONS);
        con.add(nextImage, BorderLayout.EAST);

        invalidate();
        validate();
        repaint();
    }

    private void refreshInstructions()  {
        instructImageIndex++;
        if (instructImageIndex >= instructImageDetail.length)  {
            // If this is the last image, return to menu
            window.setResizable(true);
            if (isInGame)  {
                tempMenu();
            } else  {
                menu();
            }
        } else {
            // Retrieve Instruction Image from file and scale to Window
            ImageIcon icon = new ImageIcon("Images\\" + instructImageDetail[instructImageIndex]);
            instructImage.setIcon(scale(icon, 600, 700));
        }

        invalidate();
        validate();
        repaint();

    }

    private static ImageIcon scale(ImageIcon icon, int dim1, int dim2)  {
        // Returns an ImageIcon re-scaled to the specified size
        Image image = icon.getImage();
        Image scaled = image.getScaledInstance(dim1, dim2,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    private void retrievePlayerNames()  {
        // Retrieve User Choice
        int noPlayers = (int)noPlayersChoice.getSelectedItem();
        playerNames = new String[noPlayers];
        con.removeAll();
        con.setLayout(new GridLayout((noPlayers+3),1));

        // Player Names Prompt
        JLabel namesPrompt = new JLabel("Enter Player Names");
        namesPrompt.setFont(HEADING);
        con.add(namesPrompt);

        // Player Name TextFields
        player1Name = new JTextField("Player1");
        con.add(player1Name);
        player2Name = new JTextField("Player2");
        con.add(player2Name);
        player3Name = new JTextField("Player3");
        con.add(player3Name);
        if (noPlayers > 3)  {
            player4Name = new JTextField("Player4");
            con.add(player4Name);
            if (noPlayers > 4)  {
                player5Name = new JTextField("Player5");
                con.add(player5Name);
            }
        }

        // Back Button
        backToNoSelect = new JButton("Back");
        backToNoSelect.setFont(BUTTONS);
        backToNoSelect.addActionListener(this);
        con.add(backToNoSelect);

        // Start Game Button
        startGame = new JButton("Start Game");
        startGame.setFont(BUTTONS);
        startGame.addActionListener(this);
        con.add(startGame);

        invalidate();
        validate();
        repaint();
    }

    private void savePlayerNames()  {
        playerNames[0] = player1Name.getText();
        playerNames[1] = player2Name.getText();
        playerNames[2] = player3Name.getText();
        if (playerNames.length > 3)  {
            playerNames[3] = player4Name.getText();
            if (playerNames.length > 4)  {
                playerNames[4] = player5Name.getText();
            }
        }
    }

    private void viewHand()  {
        con.removeAll();
        con.setLayout(new GridLayout(3, 6, 5, 5));

        // Instruction Label
        JLabel handViewInstruct = new JLabel("Click a card to Play it");
        handViewInstruct.setFont(HEADING);
        con.add(handViewInstruct);

        //Return to Game
        con.add(backtoGame);

        playerHandView = new ArrayList<>();
        for (int i = 0; i < newGame.getPlayerHand(0).hand.size(); i++)  {
            int cardIndex = newGame.getPlayerHand(0).hand.get(i).cardIndex;
            JLabel cardIm = cardImages[cardIndex];
            cardIm.addMouseListener(this);
            playerHandView.add(cardIm);
            con.add(playerHandView.get(i));
        }

        invalidate();
        validate();
        repaint();
    }

    public void endGame(String winStatement)  {
        con.removeAll();
        con.setLayout(new GridLayout(3,1));

        // Win Statement
        JLabel winLabel = new JLabel(winStatement);
        winLabel.setFont(HEADING);
        con.add(winLabel);

        // Back to Menu
        con.add(returnToMenu);

        invalidate();
        validate();
        repaint();
    }

    private void tempMenu()  {
        con.removeAll();
        con.setLayout(new GridLayout(4,1,20,20));

        // menu Title
        JLabel tempMenuTitle = new JLabel("In-Game menu");
        tempMenuTitle.setFont(HEADING);
        con.add(tempMenuTitle);

        // Home menu Button
        con.add(returnToMenu);

        // View Instructions Button
        con.add(instruct);

        // Return to Game Button
        con.add(backtoGame);

        invalidate();
        validate();
        repaint();
    }

    public void playerTurn(Card lastCard, int currentCategoryIndex, String option)  {
        newRound = false;
        this.lastCard = lastCard;
        this.currentCategoryIndex = currentCategoryIndex;
        refreshGameGUI(option);
    }

    public void refreshGameGUI(String option)  {
        // refreshes the Game GUI without starting a new Game and initialising labels
        con.removeAll();
        con.setLayout(new GridLayout(3, 4, 5, 5));

        // Update Player Hand details
        String[] tempText = new String[playerNames.length];
        for (int i = 1; i < playerNames.length; i++) {
            if (newGame.getPlayer(i).isWinner()) {
                tempText[i] = playerNames[i] + " has won";
            } else if (newGame.getPlayer(i).isPassed()) {
                tempText[i] = playerNames[i] + " has " + newGame.getPlayerHand(i).hand.size() + " cards \n and has passed this round";
            } else {
                tempText[i] = playerNames[i] + " has " + newGame.getPlayerHand(i).hand.size() + " cards";
            }
        }
        player2window.setText(tempText[1]);
        con.add(player2window);
        player3window.setText(tempText[2]);
        con.add(player3window);
        if (playerNames.length > 3)  {
            player4window.setText(tempText[3]);
            con.add(player4window);
            if (playerNames.length > 4)  {
                player5window.setText(tempText[4]);
                con.add(player5window);
            }
        }

        // add appropriate number of blank labels to fill the grid
        for (int i = playerNames.length-1; i < 6; i++) {
            con.add(new JLabel());
        }

        // Last Card Label
        if (lastCard != null) {
            lastCardView = cardImages[lastCard.cardIndex];
        }
        con.add(lastCardView);

        // Dialogue Box View
        dialogue = new JPanel(new GridLayout(DIALOGUE_NO,1));
        con.add(dialogue);
        for (int i = 0; i < messages.size(); i++)  {
            JLabel message = new JLabel(messages.get(i));
            message.setFont(LABELS);
            dialogue.add(message);
        }
        // View Hand Button
        con.add(viewHand);

        // Pass button
        con.add(pass);

        // Saved Space
        if (newRound && !selected)  {
            blankSpace("Category");
        } else {
            blankSpace(option);
        }

        // Temp menu Button
        con.add(tempMenu);

        invalidate();
        validate();
        repaint();
    }

    public static void message(String message)  {
        System.out.println(message);
        while (messages.size() >= DIALOGUE_NO)  {
            // Remove earliest message
            messages.remove(0);
        }
        messages.add(message);
    }

    public static void loadCardImages()  {
        int noCards = newGame.deck.cards.length;
        cardImages = new JLabel[noCards];
        for (int i = 0; i < noCards; i++)  {
            cardImages[i] = new JLabel(scale(new ImageIcon("Images\\" + newGame.deck.cards[i].fileName),240,360));
        }
    }

    private void blankSpace(String option)  {
        switch (option) {
            case "Blank":
                con.add(new JLabel());
                break;
            case "YesNo":
                JPanel panel1 = new JPanel(new GridLayout(2, 1));
                con.add(panel1);
                panel1.add(yesChoice);
                panel1.add(noChoice);
                break;
            case "Category":
                JPanel panel2 = new JPanel(new GridLayout(2, 1));
                con.add(panel2);
                String[] categories = {"hardness", "specific gravity", "cleavage", "crustal abundance", "economic value"};
                chooseCategory = new JComboBox<>(categories);
                panel2.add(chooseCategory);
                panel2.add(select);
                break;
        }
    }

    public void playerNewRound(String option)  {
        newRound = true;
        refreshGameGUI(option);
//        if (!(newGame.lastCard instanceof TrumpCard)) {
//            refreshGameGUI("Category");
//        } else {
//            selected = true;                            // Category determined by the trump card which was played
//            refreshGameGUI("Blank");
//        }
    }

    public void aiNewRound()  {
        newGame.newRoundEnd();
    }

    public void continueNewRound()  {
        if (turnHad && selected)  {
            Game.players[0].setInPlay(inPlay);
            resetBooleans();
            newGame.newRoundEnd();
        }
    }

    public void continueNewTurn()  {
        resetBooleans();
        newGame.newTurnEnd();
    }

    private void resetBooleans()  {
        turnHad = false;
        selected = false;
        newRound = false;
    }
}
