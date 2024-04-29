package common;

import pages.NativeUi;

import javax.swing.*;
import java.awt.*;

public class Scoreboard extends NativeUi {

    private JPanel panel;
    private JScrollPane scrollPane;

    // ----- C O N S T R U C T O R S -----

    public Scoreboard() {
        super();
        useNativeUI();
        initializePanel();
    }

    private void initializePanel() {

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add a title label
        JLabel title = new JLabel("Scoreboard");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title);

        // Create a JScrollPane and add the panel to it
        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Set the size of the scrollable area to preferred size
        scrollPane.setPreferredSize(new Dimension(200, 60));
    }

    // ----- M E T H O D S -----

    public void hit(int x, int y) {
        SwingUtilities.invokeLater(() -> {
            JLabel label = new JLabel("Hit: " + x + ", " + y);
            panel.add(label);
            panel.revalidate();
            panel.repaint();

            scrollPane.revalidate();
            scrollPane.repaint();
        });
    }

    public void display() {
        SwingUtilities.invokeLater(() -> {
            panel.revalidate();
            panel.repaint();

            scrollPane.revalidate();
            scrollPane.repaint();
        });
    }

    // ----- G E T T E R S - S E T T E R S -----

    public JScrollPane getPanel() {
        return scrollPane;
    }
}
