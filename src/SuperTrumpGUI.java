import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hadassah on 21/10/2016.
 * This class manages the GUI for the Project Mineral Super Trumps Game
 */
public class SuperTrumpGUI extends JFrame implements ActionListener {
    private static SuperTrumpGUI window;
    private static JButton start, quit, instruct, returnToMenu, nextImage, playersContinue, startGame, backToNoSelect;
    private static JButton tempMenu, pass, viewHand;
    private static JLabel instructImage, player2window, player3window, player4window, player5window;
    private static JComboBox<Integer> noPlayersChoice;
    private static JTextField player1Name, player2Name, player3Name, player4Name, player5Name;
    private String[] playerNames;
    private int instructImageIndex;
    private static final Dimension DIM = new Dimension(900,640);
    private static final Font HEADING = new Font("Century",Font.BOLD, 22);
    private static final Font BUTTONS = HEADING.deriveFont(12);
    private static final Font LABELS = HEADING.deriveFont(Font.PLAIN,12);
    private Container con = getContentPane();
    // TODO better way to do Instruct Images
    private String[] instructImageDetail = {"Slide61.jpg","Slide62.jpg","Slide63.jpg","Slide64.jpg"};
    public Game newGame;

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
        Menu();
    }

    private void Menu()  {
        con.removeAll();
        con.setLayout(new GridLayout(4,1,20,20));
        JLabel welcome = new JLabel("Welcome to Project Mineral: Super Trumps!");
        welcome.setFont(HEADING);
        welcome.setHorizontalAlignment(JLabel.CENTER);
        con.add(welcome);
        start = new JButton("Start Game");
        start.setFont(BUTTONS);
        start.addActionListener(this);
        con.add(start);
        instruct = new JButton("View Instructions");
        instruct.setFont(BUTTONS);
        instruct.addActionListener(this);
        con.add(instruct);
        quit = new JButton("Quit");
        quit.setFont(BUTTONS);
        quit.addActionListener(this);
        con.add(quit);
        invalidate();
        validate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == start) {
            RetrieveNoPlayers();
        }
        if (source == instruct)  {
            ViewInstructions();
        }
        if (source == quit)  {
            dispose();
        }
        if (source == returnToMenu)  {
            Menu();
        }
        if (source == nextImage)  {
            RefreshInstructions();
        }
        if (source == playersContinue)  {
            RetrievePlayerNames();
        }
        if (source == backToNoSelect)  {
            RetrieveNoPlayers();
        }
        if (source == startGame)  {
            SavePlayerNames();
            GameGUI(playerNames);
        }
        if (source == tempMenu)  {
            TempMenu();
        }
        if (source == pass)  {
            // TODO pass
        }
        if (source == viewHand)  {
            ViewHand();
        }
    }

    public void GameGUI(String[] players) {
        con.removeAll();
        con.setLayout(new GridLayout(3, 4, 5, 5));

        newGame = new Game(players.length);

        player2window = new JLabel(players[1] + " has " + "playerHand.length" + " cards");
        player2window.setFont(LABELS);
        con.add(player2window);
        player3window = new JLabel(players[2] + " has " + "playerHand.length" + " cards");
        player3window.setFont(LABELS);
        con.add(player3window);
        if (players.length > 3)  {
            player4window = new JLabel(players[3] + " has " + "playerHand.length" + " cards");
            player4window.setFont(LABELS);
            con.add(player4window);
            if (players.length > 4)  {
                player5window = new JLabel(players[4] + " has " + "playerHand.length" + " cards");
                player5window.setFont(LABELS);
                con.add(player5window);
            }
        }

        // add appropriate number of blank labels to fill the grid
        for (int i = players.length-1; i < 6; i++) {
            con.add(new JLabel());
        }
        con.add(new JLabel("Last Card"));
        con.add(new JLabel("Dialogue Box"));
        con.add(new JButton("View Hand"));
        con.add(new JButton("Pass"));
        con.add(new JLabel());                  // blank Label

        // Temp Menu Button
        tempMenu = new JButton("Menu");
        tempMenu.setFont(BUTTONS);
        con.add(tempMenu);

        invalidate();
        validate();
        repaint();
    }

    private void RetrieveNoPlayers()  {
        con.removeAll();
        con.setLayout(new GridLayout(10,1));

        // Number of Players prompt
        JLabel playerNoPrompt = new JLabel("Select number of players from the list");
        playerNoPrompt.setFont(HEADING);
        con.add(playerNoPrompt);

        // Drop-Down-List
        Integer[] numbersArray = {3, 4, 5};
        noPlayersChoice = new JComboBox<>(numbersArray);
        con.add(noPlayersChoice);

        // Continue Button
        playersContinue = new JButton("Continue");
        playersContinue.setFont(BUTTONS);
        playersContinue.addActionListener(this);
        con.add(playersContinue);

        invalidate();
        validate();
        repaint();
    }

    private void ViewInstructions()  {
        con.removeAll();
        window.setResizable(false);
        instructImageIndex = 0;
        con.setLayout(new BorderLayout());

        // Return To Menu Button
        returnToMenu = new JButton("Return to Menu");
        returnToMenu.setFont(BUTTONS);
        returnToMenu.addActionListener(this);
        con.add(returnToMenu, BorderLayout.WEST);

        // Retrieve Instruction Image from file and Scale to Window
        ImageIcon icon = new ImageIcon("Images\\" + instructImageDetail[instructImageIndex]);
        instructImage = new JLabel(Scale(icon, 600, 700));
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

    private void RefreshInstructions()  {
        instructImageIndex++;
        if (instructImageIndex >= instructImageDetail.length)  {
            // If this is the last image, return to Menu
            window.setResizable(true);
            Menu();
        }else {
            // Retrieve Instruction Image from file and Scale to Window
            ImageIcon icon = new ImageIcon("Images\\" + instructImageDetail[instructImageIndex]);
            instructImage.setIcon(Scale(icon, 600, 700));

            invalidate();
            validate();
            repaint();
        }
    }

    private ImageIcon Scale(ImageIcon icon, int dim1, int dim2)  {
        // Returns an ImageIcon re-scaled to the specified size
        Image image = icon.getImage();
        Image scaled = image.getScaledInstance(dim1, dim2,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    private void RetrievePlayerNames()  {
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

    private void SavePlayerNames()  {
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

    private void ViewHand()  {
        // TODO view Hand
    }

    private void TempMenu()  {
        // TODO Temp Menu
    }
}
