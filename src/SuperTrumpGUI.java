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
    private static JButton start, quit, instruct, returnToMenu, nextImage;
    private static JLabel instructImage;
    private int instructImageIndex;
    private static final Dimension DIM = new Dimension(900,640);
    private static final Font HEADING = new Font("Century",Font.BOLD, 22);
    private static final Font BUTTONS = HEADING.deriveFont(12);
    private static final Font LABELS = HEADING.deriveFont(Font.PLAIN,12);
    private Container con = getContentPane();
    private String[] instructImageDetail = {"1","2","3"};

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
            InitiateGame();
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
    }

    public void GameGUI(int noPlayers) {
        con.removeAll();

        con.setLayout(new GridLayout(3, 4, 5, 5));
        for (int i = 0; i < noPlayers-1; i++) {
            JLabel player = new JLabel("Player" + (i + 1) + " has 3 cards");
            con.add(player);
        }
        for (int i = noPlayers-1; i < 6; i++) {
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

    private void InitiateGame()  {
        con.removeAll();
        con.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel playerNoPrompt = new JLabel("Select number of players from the list");
        playerNoPrompt.setFont(LABELS);
    }

    public void ViewInstructions()  {
        con.removeAll();
        instructImageIndex = 0;
        con.setLayout(new BorderLayout());
        returnToMenu = new JButton("Return to Menu");
        returnToMenu.setFont(BUTTONS);
        returnToMenu.addActionListener(this);
        con.add(returnToMenu, BorderLayout.WEST);
        instructImage = new JLabel(instructImageDetail[instructImageIndex]);
        con.add(instructImage, BorderLayout.CENTER);
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
            Menu();
        }else {
            instructImage.setText(instructImageDetail[instructImageIndex]);
            invalidate();
            validate();
            repaint();
        }
    }
}
