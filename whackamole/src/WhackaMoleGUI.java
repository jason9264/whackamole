import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * Provides the view and controller for the Whack a Mole Game
 * @author Suzanne Balik
 * @author Michelle Glatz
 */
public class WhackaMoleGUI extends JFrame implements ActionListener {


    /** Width of GUI Window */
    public static final int WIDTH = 700;
    
    /** Height of GUI Window */
    public static final int HEIGHT = 650;

    /** X Location for GUI */
    public static final int X = 100;

    /** Y Location for GUI */
    public static final int Y = 20;

    /** Path to (directory for) symbol images */
    public static final String PATH = "images/";
    
    /** Extension of symbol images */
    public static final String EXTENSION = ".gif";

    /** Maximum time (milliseconds) that symbols are displayed */
    public static final int MAX_DISPLAY_TIME = 30000;
    
    /** Time (milliseconds) that symbols are displayed in testing mode*/
    public static final int TEST_DISPLAY_TIME = 1500;
    
    /** Time (milliseconds) limit for game play */
    public static final int MAX_GAME_TIME = 20000;

    /** Normal bold font */
    public static final Font NORMAL_BOLD = new Font("Courier", 1, 16);
    
    /** Normal font*/
    public static final Font NORMAL = new Font("Courier", 0, 16);

    /** Displayed symbol row */
    private int displayRow;
    
    /** Displayed symbol column */
    private int displayCol;

    /** Instructions label */
    private JLabel instructionsLabel;
    
    /** Total score label */
    private JLabel totalScoreLabel;
    
    /** Number of misses label */
    private JLabel numberOfMissesLabel;
    
    /** Statistics panel */
    private JPanel statisticsPanel;
    
    /** Information (instructions and statistics) panel */
    private JPanel informationPanel;

    /** Symbol buttons */
    private JButton[][] gridButtons;
    
    /** Panel to display grid of symbols */
    private JPanel gridPanel;
    
    /** Play button */
    private JButton playButton;
    
    /** Quit button */
    private JButton quitButton;
    
    /** Panel to hold game buttons */
    private JPanel buttonPanel;

    /** WhackaMole (model) instance */
    private WhackaMole game;

    /** Timer to display symbol for short period of time */
    private javax.swing.Timer timer;
    
    /** Timer for game time limit */
    private javax.swing.Timer gameTimer;
    
    /** If more time left in game */
    private boolean moreTime = true;

    /** If game is being run in testing mode */
    private boolean testing;

    /**
     * Creates GUI for playing Whack A Mole game
     * @param testing true if in testing mode, false otherwise
     */
    public WhackaMoleGUI(boolean testing) {

        super("Whack A Mole");
        this.testing = testing;
        setSize(WIDTH, HEIGHT);
        setLocation(X, Y);
        Container c = getContentPane();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Instructions and statistics
        informationPanel = new JPanel();
        informationPanel.setLayout(new BorderLayout());
        instructionsLabel = new JLabel
        (" Welcome to the Whack A Mole Game -- Press PLAY to start a new game!");
        //instructionsLabel.setFont(NORMAL_BOLD); does not render correctly with openjdk 
        //version "11.0.5"
        instructionsLabel.setFont(NORMAL);
        informationPanel.add(instructionsLabel, BorderLayout.NORTH);
        statisticsPanel = new JPanel();
        totalScoreLabel = new JLabel(" Total Score: 0");
        totalScoreLabel.setFont(NORMAL);
        numberOfMissesLabel = new JLabel(" Number of Misses: 0");
        numberOfMissesLabel.setFont(NORMAL);
        statisticsPanel.add(totalScoreLabel);
        statisticsPanel.add(numberOfMissesLabel);
        informationPanel.add(statisticsPanel, BorderLayout.CENTER);
        c.add(informationPanel,BorderLayout.NORTH);

        //Grid of symbols
        gridPanel = new JPanel(new GridLayout(WhackaMole.ROWS, WhackaMole.COLS));
        gridButtons = new JButton[WhackaMole.ROWS][WhackaMole.COLS];
        game = new WhackaMole(testing);

        //Add enabled buttons with no ActionListeners so they are not greyed out,
        //but do not generate events when pushed
        for (int i = 0; i < gridButtons.length; i++) {
            for (int j = 0; j < gridButtons[i].length; j++) {
                JButton button = new JButton("" + game.getSymbolPoints(i,j),
                                            new ImageIcon(PATH +  game.getSymbolName(i,j) +
                                                          EXTENSION ));
                gridButtons[i][j] = button;
                gridPanel.add(button);
            }
        }
        c.add(gridPanel,BorderLayout.CENTER);

        //Game play buttons
        buttonPanel = new JPanel();
        playButton = new JButton("PLAY");
        playButton.addActionListener(this);
        quitButton = new JButton("QUIT");
        quitButton.addActionListener(this);
        buttonPanel.add(playButton);
        buttonPanel.add(quitButton);
        c.add(buttonPanel, BorderLayout.SOUTH);

        if (testing) {
            timer = new javax.swing.Timer(TEST_DISPLAY_TIME, this);
            gameTimer = new javax.swing.Timer(2 * MAX_GAME_TIME, this); 
        }
        else {
            timer = new javax.swing.Timer(MAX_DISPLAY_TIME, this);
            gameTimer = new javax.swing.Timer(MAX_GAME_TIME, this);   
        }

        setVisible(true);
    }

    /**
     * Handles game play based on events generated by buttons (symbol, play, quit) and timer
     * @param e event that controls game play.
     */
    public void actionPerformed(ActionEvent e) {

        //Quit Game
        if (e.getSource() == quitButton) {
            System.exit(1);
        }
        
        //No Time Left in Game
        if (e.getSource() == gameTimer) {
            instructionsLabel.setText("                              GAME OVER");
            playButton.setText("PLAY");
            timer.stop();
            moreTime = false;
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (!game.hasBeenClickedOn(i,j)) {
                        gridButtons[i][j].setEnabled(false);
                    }

                }
            }
        }   

        //Play Game
        else if (e.getSource() == playButton) {
            // In test mode, allow stopping at end of test
            // to preserve GUI state at that point, making recording of
            // actual results easier
            if (playButton.getText().equals("STOP")) {
                playButton.setText("PLAY");
                timer.stop();
                gameTimer.stop();
            } else {
                game = new WhackaMole(testing);
                instructionsLabel.setText(
                                  "         Play Game -- click on an animal to score points!");
                totalScoreLabel.setText(" Total Score: 0");
                numberOfMissesLabel.setText(" Number of Misses: 0");
                
                //Blank out all buttons
                for (int i = 0; i < gridButtons.length; i++) {
                    for (int j = 0; j < gridButtons[i].length; j++) {
                        gridButtons[i][j].addActionListener(this);
                        gridButtons[i][j].setText("");
                        gridButtons[i][j].setIcon (new ImageIcon(PATH + "blank" + EXTENSION));
                        gridButtons[i][j].setBackground(Color.WHITE);
                        gridButtons[i][j].setEnabled(false);
            
                    }
                }
                //Get row and col for first symbol to be displayed
                displayRow = game.getNextRow();
                displayCol = game.getNextCol();
            
                //Update button icon/label and enable it
                String symbolName = game.getSymbolName(displayRow,displayCol);
                int symbolPoints = game.getSymbolPoints(displayRow, displayCol);
                gridButtons[displayRow][displayCol].setText("" + symbolPoints);
                gridButtons[displayRow][displayCol].setIcon(new ImageIcon(PATH + symbolName +
                                                                EXTENSION));
                gridButtons[displayRow][displayCol].setEnabled(true);
                //Display symbols that are worth more points for a shorter period of time
                
                moreTime = true; 
                
                if (!testing) {
                    timer.setInitialDelay(MAX_DISPLAY_TIME / symbolPoints);   
                } else {  
                    playButton.setText("STOP");
                }
                timer.restart();
                gameTimer.restart();
            }

        }

        //Timer went off before a grid button was clicked
        else if (e.getSource() == timer) {
            timer.stop();
            game.addMiss();
            numberOfMissesLabel.setText(" Number of Misses: " + game.getNumberOfMisses());
            //Blank out and disable grid button
            gridButtons[displayRow][displayCol].setEnabled(false);
            gridButtons[displayRow][displayCol].setText("");
            gridButtons[displayRow][displayCol].setIcon (new ImageIcon(PATH + "blank" + EXTENSION));

            //Get row and col for next symbol to be displayed
            displayRow = game.getNextRow();
            displayCol = game.getNextCol();

            //Update button icon/label and enable it
            String symbolName = game.getSymbolName(displayRow,displayCol);
            int symbolPoints = game.getSymbolPoints(displayRow, displayCol);
            gridButtons[displayRow][displayCol].setText("" + symbolPoints);
            gridButtons[displayRow][displayCol].setIcon(new ImageIcon(PATH + symbolName +
                                                        EXTENSION));
            gridButtons[displayRow][displayCol].setEnabled(true);

            //Display symbols that are worth more points for a shorter period of time
            if (!testing) {
                if (moreTime) {
                    timer.setInitialDelay(MAX_DISPLAY_TIME / symbolPoints);
                }
            }
            if (moreTime) {
                timer.restart();
            }

        }

        //Click on symbol button
        else {
            timer.stop();
            boolean found = false;
            for (int i = 0; !found && i < gridButtons.length; i++) {
                for (int j = 0; !found && j < gridButtons[i].length; j++) {
                    if (gridButtons[i][j] == e.getSource()) {
                        found = true;
                        gridButtons[i][j].removeActionListener(this);
                        gridButtons[i][j].setBackground(Color.YELLOW);
                        gridButtons[i][j].setOpaque(true);
                        gridButtons[i][j].setBorderPainted(false);
                        game.clickOnSymbol(i,j);
                        totalScoreLabel.setText(" Total Score: " + game.getTotalScore());
                        if (game.allSymbolsClickedOn()) {
                            instructionsLabel.setText("                              GAME OVER");
                            playButton.setText("PLAY");
                        }
                        else {
                            //Get row and col for next symbol to be displayed
                            displayRow = game.getNextRow();
                            displayCol = game.getNextCol();

                            //Update button icon/label and enable it
                            String symbolName = game.getSymbolName(displayRow,displayCol);
                            int symbolPoints = game.getSymbolPoints(displayRow, displayCol);
                            gridButtons[displayRow][displayCol].setText("" + symbolPoints);
                            gridButtons[displayRow][displayCol].setIcon(new ImageIcon(PATH +
                                                                        symbolName + EXTENSION));
                            gridButtons[displayRow][displayCol].setEnabled(true);
                             //Display symbols that are worth more points for shorter period of time
                            if (!testing) {
                                if (moreTime) {
                                    timer.setInitialDelay(MAX_DISPLAY_TIME / symbolPoints);
                                }
                            }
                            if (moreTime) {           
                                timer.restart();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Starts WhackaMoleGUI in normal mode if no command line argument is provided,
     * in testing mode if single command line argument is -t. Otherwise, provides a
     * usage message
     * @param args command line argument, if args[0] is "-t", game is played in testing mode
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            new WhackaMoleGUI(false); //Normal game mode
        }
        else if (args.length == 1 && args[0].equals("-t")) {
            new WhackaMoleGUI(true);  //Testing mode
        }
        else {
            System.out.println("Usage: java WhackaMoleGUI [-t]");
        }
    }

}
