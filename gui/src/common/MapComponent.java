package common;
import map.Map;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MapComponent
{
    private Map map;
    private JPanel panel;

    // ----- C O N S T R U C T O R S -----

    public MapComponent()
    {
        map = new Map();
        initializePanel();
    }

    public MapComponent(Map map)
    {
        this.map = map;
        initializePanel();
    }

    // ----- M E T H O D S -----

    private void initializePanel() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(10, 10));  // Assuming the map is 10x10
        List<List<Object>> mapData = map.getMap();
        for (List<Object> row : mapData) {
            for (Object element : row) {
                JButton button = new JButton(element.toString());
                panel.add(button);
            }
        }
    }

    public void display()
    {
        // Assuming this method is called to refresh the display
        panel.revalidate();
        panel.repaint();
    }

    // ----- G E T T E R S -----
    public JPanel getPanel() {
        return panel;
    }
}
