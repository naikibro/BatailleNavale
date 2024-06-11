package pages;

import common.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LandingPage extends NativeUi {

    private JTextField player1Name, player2Name;
    private JButton playButton;
    public ActionListener playAction;

    public LandingPage(Player player1, Player player2) {
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

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel namePanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel nameLabel1 = new JLabel("Player 1");
        player1Name = new JTextField(20);
        namePanel1.add(nameLabel1);
        namePanel1.add(player1Name);

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(namePanel1, gbc);

        JPanel namePanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel nameLabel2 = new JLabel("Player 2");
        player2Name = new JTextField(20);
        namePanel2.add(nameLabel2);
        namePanel2.add(player2Name);

        gbc.gridy = 1;
        centerPanel.add(namePanel2, gbc);

        playButton = new JButton("Play");
        playButton.addActionListener(actionEvent -> {
            player1.setName(player1Name.getText());
            player2.setName(player2Name.getText());
            if (playAction != null) {
                playAction.actionPerformed(actionEvent);
            }
        });

        gbc.gridy = 2;
        centerPanel.add(playButton, gbc);
    }

    public void setPlayAction(ActionListener playAction) {
        this.playAction = playAction;
    }
}
