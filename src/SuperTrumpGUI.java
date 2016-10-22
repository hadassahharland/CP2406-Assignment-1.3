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
    private static JLabel instructImage;
    private static JComboBox<Integer> noPlayersChoice;
    private int instructImageIndex;
    private static final Dimension DIM = new Dimension(900,640);
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
    }

    public void GameGUI(String[] players) {
        con.removeAll();

        con.setLayout(new GridLayout(3, 4, 5, 5));
        for (int i = 0; i < players.length-1; i++) {
            JLabel player = new JLabel("Player" + (i + 1) + " has 3 cards");
            con.add(player);
        }
        for (int i = players.length-1; i < 6; i++) {
            con.add(new JLabel());
        }
        con.add(new JLabel("Last Card"));
        con.add(new JLabel("Dialogue Box"));
        con.add(new JButton("View Hand"));
        con.add(new JButton("Pass"));
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

    public void ViewInstructions()  {
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

    public void RefreshInstructions()  {
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

    public ImageIcon Scale(ImageIcon icon, int dim1, int dim2)  {
        // Returns an ImageIcon re-scaled to the specified size
        Image image = icon.getImage();
        Image scaled = image.getScaledInstance(dim1, dim2,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public void RetrievePlayerNames()  {
        // Retrieve User Choice
        int noPlayers = (int)noPlayersChoice.getSelectedItem();
        con.removeAll();
        con.setLayout(new GridLayout((noPlayers+2),2));

        // Player Names Prompt
        JLabel namesPrompt = new JLabel("Enter Player Names");
        namesPrompt.setFont(HEADING);
        con.add(namesPrompt);
        con.add(new JLabel());                  // Empty Label to fill space

        // Player Name TextFields
        con.add(new JLabel("Player 1:"));
        JTextField player1Name = new JTextField();
        con.add(player1Name);
        con.add(new JLabel("Player 2:"));
        JTextField player2Name = new JTextField();
        con.add(player2Name);
        con.add(new JLabel("Player 3:"));
        JTextField player3Name = new JTextField();
        con.add(player3Name);
        if (noPlayers > 3)  {
            con.add(new JLabel("Player 4:"));
            JTextField player4Name = new JTextField();
            con.add(player4Name);
            if (noPlayers > 4)  {
                con.add(new JLabel("Player 5:"));
                JTextField player5Name = new JTextField();
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
}
