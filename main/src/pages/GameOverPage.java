package pages;

import common.Player;
import common.Scoreboard;

import javax.swing.*;
import java.awt.*;

public class GameOverPage extends NativeUi {

    private Scoreboard scoreboard;
    private JFrame frame;
    private Player player1;
    private Player player2;

    public GameOverPage(Scoreboard scoreboard, Player player1, Player player2) {
        super();
        this.scoreboard = scoreboard;
        this.player1 = player1;
        this.player2 = player2;
        useNativeUI();

        // Create and set up the window
        int windowWidth = 1000;
        int windowHeight = 800;

        frame = new JFrame("Rapport de bataille");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
        frame.setLayout(new BorderLayout());

        // Add a button to close the window and stop the app
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });

        // Add components to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Optionally, you can add more components to display game over information
        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(gameOverLabel, BorderLayout.CENTER);
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
