package pages;

import common.Player;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.*;
import java.awt.event.ActionListener;

public class LandingPage extends NativeUi {

    private JTextField playerName;
    private JButton playButton;
    public ActionListener playAction;

    public LandingPage(Player player) {
        super();
        useNativeUI();

        int windowWidth = 1000;
        int windowHeight = 800;

        frame = new JFrame("Bataille Navale");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Bataille Navale", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(label, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel nameLabel = new JLabel("Player 1 name:");
        playerName = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(playerName);

        playButton = new JButton("Play");  // Initialize the button
        playButton.addActionListener(actionEvent -> {
            player.setName(playerName.getText());
        });

        namePanel.add(playButton);
        centerPanel.add(namePanel);
    }

    public void setPlayAction(ActionListener playAction) {
        this.playButton.addActionListener(playAction);
    }

}
