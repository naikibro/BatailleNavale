package common;

import map.Map;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A graphical component representing a Map.
 * This class initializes a JPanel with buttons corresponding to each Tile in the Map.
 * The buttons interact with a Scoreboard to register hits.
 */
public class MapComponent {
    private Map map;
    private JPanel panel;
    private Scoreboard scoreboard;

    // ----- C O N S T R U C T O R S -----

    /**
     * Constructs a MapComponent with a new Map and a given Scoreboard.
     * @param sboard The Scoreboard object used to register hits.
     */
    public MapComponent(Scoreboard sboard) {
        map = new Map();
        scoreboard = sboard;
        initializePanel();
    }

    /**
     * Constructs a MapComponent with a given Map.
     * @param map The Map object to use for this component.
     */
    public MapComponent(Map map) {
        this.map = map;
        initializePanel();
    }

    // ----- M E T H O D S -----

    /**
     * Initializes the panel with buttons representing each Tile in the Map.
     * The buttons interact with the Scoreboard to register hits.
     */
    private void initializePanel() {
        int i = 1;
        int j;
        panel = new JPanel();
        panel.setLayout(new GridLayout(10, 10));

        List<List<Object>> mapData = map.getMap();

        for (List<Object> row : mapData) {
            final int rowIndex = i;
            i++;
            j = 1;
            for (Object element : row) {
                final int colIndex = j;
                j++;
                JButton button = new JButton(element.toString());

                /**
                 * Adds an action listener to the button.
                 * This listener responds to click events on a Tile.
                 */
                button.addActionListener(e -> {
                    // TODO: Handles the clicks on a Tile
                    System.out.println("x: " + colIndex + ", y: " + rowIndex);
                    scoreboard.hit(colIndex, rowIndex);
                });
                panel.add(button);
            }
        }
    }

    /**
     * Refreshes and repaints the panel display.
     */
    public void display() {
        panel.revalidate();
        panel.repaint();
    }

    // ----- G E T T E R S -----

    /**
     * Returns the JPanel representing this MapComponent.
     * @return The JPanel object associated with this component.
     */
    public JPanel getPanel() {
        return panel;
    }
}
