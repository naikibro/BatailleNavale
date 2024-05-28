package common;

import map.Map;
import map.Tile;

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
    private Player player1, player2;

    // ----- C O N S T R U C T O R S -----
        public MapComponent(Scoreboard sboard, Player player1, Player player2) {

        scoreboard = sboard;
        this.player1 = player1;
        this.player2 = player2;
        this.map = player2.getPlayerMap();
        initializePanel();
    }

    // ----- M E T H O D S -----

    /**
     * Initializes the panel with buttons representing each Tile in the Map.
     * The buttons interact with the Scoreboard to register hits.
     */
    private void initializePanel() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(10, 10));

        List<List<Tile>> mapData = map.getMap();

        for (List<Tile> row : mapData) {
            for (Tile tile : row) {
                JButton button = new JButton();

                /**
                 * Adds an action listener to the button.
                 * This listener responds to click events on a Tile.
                 */
                button.addActionListener(e -> {
                    int x = tile.getX();
                    int y = tile.getY();

                    System.out.println("click on x: " + x + ", y: " + y + " id_ship = " + tile.getId_ship());

                    // TODO : implement the Tile handling logic
                    // TODO : implement the scoreboard logic
                    scoreboard.hit(this.player1, x, y);
                    scoreboard.miss(this.player1, x, y);
                    scoreboard.destroy(this.player1, x, y);
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
