package pages;

import common.MapComponent;
import javax.swing.*;
import java.awt.*;

public class WarfieldPage extends NativeUi {

    private MapComponent mapComponent;

    public WarfieldPage() {
        super();
        useNativeUI();

        // Create and set up the window
        int windowWidth = 1000;
        int windowHeight = 800;

        frame = new JFrame("Bataille Navale");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Bataille Navale", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(title, BorderLayout.NORTH);

        // Initialize and add the MapComponent
        mapComponent = new MapComponent();
        frame.add(mapComponent.getPanel(), BorderLayout.CENTER);
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
