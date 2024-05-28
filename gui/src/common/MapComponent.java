package common;

import map.Map;
import map.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class MapComponent {
    private Map map;
    private JPanel panel;
    private Scoreboard scoreboard;
    private Player player1, player2;
    private HashMap<Tile, JButton> buttonMap;

    public MapComponent(Player player1, Player player2) {
        this.scoreboard = Game.scoreboard;
        this.player1 = player1;
        this.player2 = player2;
        this.map = player2.getPlayerMap();
        this.buttonMap = new HashMap<>();
        initializePanel();
    }

    private void initializePanel() {
        panel = new JPanel(new GridLayout(10, 10));
        panel.removeAll();
        buttonMap.clear();

        for (List<Tile> row : map.getMap()) {
            for (Tile tile : row) {
                JButton button = new JButton();
                button.setBackground(getTileColor(tile));
                button.setOpaque(true);
                buttonMap.put(tile, button);
                button.addActionListener(e -> handleTileClick(tile, button));
                panel.add(button);
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    private void handleTileClick(Tile tile, JButton button) {
        int x = tile.getX();
        int y = tile.getY();

        System.out.println("Click on x: " + x + ", y: " + y + " id_ship = " + tile.getId_ship());

        if (tile.isShip()) {
            scoreboard.hit(player1, x, y);
            tile.setHit(true);
            if (tile.isDestroyed()) {
                scoreboard.destroy(player1, x, y);
            }
        } else {
            scoreboard.miss(player1, x, y);
            tile.setHit(true);
        }
    }

    public void updatePlayers(Player currentPlayer, Player enemyPlayer) {
        this.player1 = currentPlayer;
        this.player2 = enemyPlayer;
        this.map = enemyPlayer.getPlayerMap();
        initializePanel();
        display();
    }

    public void display() {
        panel.revalidate();
        panel.repaint();
    }

    public JPanel getPanel() {
        return panel;
    }

    private Color getTileColor(Tile tile) {
        if (tile.isHit()) {
            return tile.isShip() ? Color.RED : Color.BLUE;
        } else {
            return Color.GRAY;
        }
    }
}
