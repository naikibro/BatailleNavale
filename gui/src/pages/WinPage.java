package pages;

import common.Player;
import common.Scoreboard;

import javax.swing.*;
import java.awt.*;

public class WinPage extends NativeUi {

    private Player winner;
    private JFrame frame;

    public WinPage(Player winner) {
        super();
        this.winner = winner;
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
        JLabel gameOverLabel = new JLabel(this.winner.getName() + " Wins the game", SwingConstants.CENTER);
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
