import javax.swing.*;
import java.awt.*;

public class WarfieldPage extends NativeUi {

    public WarfieldPage() {
        super();
        useNativeUI();

        // Create and set up the window
        int windowWidth = 1000;
        int windowHeight = 800;

        frame = new JFrame("Bataille Navale");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(windowWidth, windowHeight));

        // Using BorderLayout to manage major areas
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Bataille Navale", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(title, BorderLayout.NORTH);
    }
}
