import javax.swing.*;

public class NativeUi {

    protected JFrame frame;

    public void useNativeUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void display() {
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }
}
