package common;

import map.Map;
import map.Tile;
import pages.WarfieldPage;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class MapComponent {
    private Map map;
    private JPanel panel;
    private Scoreboard scoreboard;
    private Player player1, player2;
    private HashMap<Tile, JComponent> buttonMap;
    private WarfieldPage warfieldPage;

    public MapComponent(Player player1, Player player2, WarfieldPage page) {
        this.scoreboard = Game.scoreboard;
        this.player1 = player1;
        this.player2 = player2;
        this.map = player2.getPlayerMap();
        this.buttonMap = new HashMap<>();
        this.warfieldPage = page;
        initializePanel(true);
    }

    private void initializePanel(boolean isInit) {
        panel = new JPanel(new GridLayout(10, 10));
        panel.removeAll();

        if (isInit){
            buttonMap.clear();
        }

        for (List<Tile> row : map.getMap()) {
            for (Tile tile : row) {
                JButton button = new JButton();
                button.setBackground(getTileColor(tile));
                button.setOpaque(true);
                buttonMap.put(tile, button);
                button.addActionListener(e -> handleTileClick(tile, button, panel));
                panel.add(button);
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    private void handleTileClick(Tile tile, JButton button, JPanel panel) {
        int x = tile.getX();
        int y = tile.getY();

        System.out.println("Click on x: " + x + ", y: " + y + " id_ship = " + tile.getId_ship());

        // TODO: handle the button clickable state
        if(! button.isFocusable())
        {
            return;
        }

        if (tile.isShip()) {
            scoreboard.hit(player1, x, y);
            tile.setHit(true);
            button.setBackground(Color.ORANGE);
            if (tile.isDestroyed()) {
                scoreboard.destroy(player1, x, y);
                button.setBackground(Color.RED);
            }
        } else {
            scoreboard.miss(player1, x, y);
            tile.setHit(true);
            button.setBackground(Color.BLUE);
        }

        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        initializePanel(false);

        // TODO: handle next player turn logic
        button.setFocusable(false);
        Game.turn += 1;
        warfieldPage.revalidateComponents(true);
    }

    public void updatePlayers(Player currentPlayer, Player enemyPlayer) {
        this.player1 = currentPlayer;
        this.player2 = enemyPlayer;
        this.map = enemyPlayer.getPlayerMap();
        initializePanel(false);
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
