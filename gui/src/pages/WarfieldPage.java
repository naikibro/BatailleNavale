package pages;

import common.Game;
import common.MapComponent;
import common.Player;
import common.Scoreboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WarfieldPage extends NativeUi {

    private MapComponent mapComponent;
    private Scoreboard scoreboard;
    private JFrame frame;
    private JPanel rightPanel;
    private Player player1;
    private Player player2;

    ActionListener endGameAction;

    public WarfieldPage(Scoreboard sboard, Player player1, Player player2) {
        super();
        this.scoreboard = sboard;
        this.player1 = player1;
        this.player2 = player2;

        useNativeUI();

        // Create and set up the window
        int windowWidth = 1000;
        int windowHeight = 800;

        frame = new JFrame("Bataille Navale");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
        frame.setLayout(new BorderLayout());

        // ----- WINDOW TITLE -----
        JLabel title = new JLabel("Bataille Navale", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(title, BorderLayout.NORTH);

        // ----- MAP -----
        this.mapComponent = new MapComponent(getCurrentPlayer(), getEnnemyPlayer());
        frame.add(mapComponent.getPanel(), BorderLayout.CENTER);

        // ----- SCOREBOARD -----
        this.rightPanel = getScoreboardPanel(scoreboard);
        frame.add(rightPanel, BorderLayout.EAST);

        // ----- DEBUG - PANEL -----
        JPanel debugPanel = getDebugPanel(player1, player2);
        frame.add(debugPanel, BorderLayout.SOUTH);
    }

    private JPanel getScoreboardPanel(Scoreboard scoreboard) {
        JPanel rightPanel = new JPanel(new GridBagLayout());

        Player currentPlayer = getCurrentPlayer();

        // Apply a constrained Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.67;

        rightPanel.add(scoreboard.getPanel(), gbc);

        // Loads the player image
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(currentPlayer.imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Resize the image
        Image scaledImage = myPicture.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
        picLabel.setHorizontalAlignment(JLabel.CENTER);
        picLabel.setVerticalAlignment(JLabel.TOP);

        gbc.gridy = 1;
        gbc.weighty = 0.33; // Image should take up one-third of the height
        rightPanel.add(picLabel, gbc);

        // Display the player's turn
        JLabel turn = new JLabel("It's " + currentPlayer.getName() + "'s turn to play");
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        rightPanel.add(turn, gbc);

        JButton nextTurn = new JButton("Validate orders");
        nextTurn.addActionListener(e -> {
            Game.turn += 1;
            revalidateComponents(false);

            this.mapComponent = new MapComponent(getCurrentPlayer(), getEnnemyPlayer());

            this.mapComponent.display();
            this.revalidateComponents(true);
        });

        gbc.gridy = 3;
        gbc.weighty = 0.1;
        rightPanel.add(nextTurn, gbc);

        return rightPanel;
    }

    private Player getCurrentPlayer() {
        int gameTurn = Game.turn;
        return (gameTurn % 2 == 0) ? this.player1 : this.player2;
    }

    private Player getEnnemyPlayer() {
        int gameTurn = Game.turn;
        return (gameTurn % 2 == 1) ? this.player1 : this.player2;
    }

    private JPanel getDebugPanel(Player player1, Player player2) {
        JPanel debugPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        debugPanel.setLayout(new BorderLayout());
        leftPanel.setLayout(new BorderLayout());

        JButton button1 = new JButton("PLAYER 1 STATS");
        button1.addActionListener(e -> player1.declineIdentity());

        JButton button2 = new JButton("PLAYER 2 STATS");
        button2.addActionListener(e -> player2.declineIdentity());

        JButton button3 = new JButton("END GAME");
        button3.addActionListener(actionEvent -> {
            if(endGameAction != null)
            {
                endGameAction.actionPerformed(actionEvent);
            }
        });

        leftPanel.add(button1, BorderLayout.WEST);
        leftPanel.add(button2, BorderLayout.EAST);
        debugPanel.add(leftPanel, BorderLayout.WEST);
        debugPanel.add(button3, BorderLayout.EAST);
        return debugPanel;
    }

    public void setEndGameAction(ActionListener endGameAction)
    {
        this.endGameAction = endGameAction;
    }

    public void revalidateComponents(boolean updatePlayers) {

        if(updatePlayers)
        {
            this.mapComponent.updatePlayers(getCurrentPlayer(), getEnnemyPlayer());
        }

        // Revalidate the scoreboard panel
        frame.remove(rightPanel);
        rightPanel = getScoreboardPanel(scoreboard);
        frame.add(rightPanel, BorderLayout.EAST);

        // Revalidate and repaint the frame to reflect changes
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void display() {
        SwingUtilities.invokeLater(() -> {
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public void hide() {
        frame.setVisible(false);
    }
}
