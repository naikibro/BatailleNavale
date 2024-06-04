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
        this.panel = new JPanel(new GridLayout(10, 10));
        this.panel.removeAll();

        if (isInit){
            buttonMap.clear();
        }

        for (List<Tile> row : map.getMap()) {
            for (Tile tile : row) {
                JButton button = new JButton();
                Color tileColor = getTileColor(tile);

                if(tile.isHit()){
                    disableButton(button);
                }

                button.setBackground(tileColor);
                button.setOpaque(true);
                buttonMap.put(tile, button);
                button.addActionListener(e -> handleTileClick(tile, button));
                this.panel.add(button);
            }
        }
        this.panel.revalidate();
        this.panel.repaint();
    }

    private void handleTileClick(Tile tile, JButton button) {
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
        button.setFocusable(false);
        disableButton(button);
        display();

        Timer timer = new Timer(3000, e -> {
            Game.turn += 1;
            this.warfieldPage.revalidateComponents(true);
        });

        timer.setRepeats(false); // Ensure the timer only runs once
        timer.start();
    }

    public void updatePlayers(Player currentPlayer, Player enemyPlayer) {
        this.player1 = currentPlayer;
        this.player2 = enemyPlayer;
        this.map = enemyPlayer.getPlayerMap();
        initializePanel(true);
        display();
    }

    public void display() {
        panel.revalidate();
        panel.repaint();
    }

    // ----- S E T T E R S -----
    public void disableButton(JButton button){
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setFocusable(false);
    }

    // ----- G E T T E R S -----

    public JPanel getPanel() {
        return this.panel;
    }

    private Color getTileColor(Tile tile) {
        if (tile.isHit()) {
            return tile.isShip() ? Color.RED : Color.BLUE;
        } else {
            return Color.GRAY;
        }
    }
}