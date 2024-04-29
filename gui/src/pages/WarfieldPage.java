package pages;

import common.MapComponent;
import common.Scoreboard;

import javax.swing.*;
import java.awt.*;

public class WarfieldPage extends NativeUi {

    private MapComponent mapComponent;
    private Scoreboard scoreboard;

    public WarfieldPage(Scoreboard sboard) {
        super();
        scoreboard = sboard;
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
        mapComponent = new MapComponent(scoreboard);
        frame.add(mapComponent.getPanel(), BorderLayout.CENTER);

        // ----- SCOREBOARD -----
        frame.add(scoreboard.getPanel(), BorderLayout.EAST);
    }

    @Override
    public void display() {
        SwingUtilities.invokeLater(() -> {
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
