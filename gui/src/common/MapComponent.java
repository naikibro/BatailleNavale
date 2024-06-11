package common;

import map.Map;
import map.Tile;
import pages.GameOverPage;
import pages.WarfieldPage;
import pages.WinPage;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapComponent {
    private Map map;
    private JPanel panel;
    private Scoreboard scoreboard;
    private Player player1, player2;
    private HashMap<Tile, JComponent> buttonMap;
    private WarfieldPage warfieldPage;
    private WinPage winPage;
    private List<JButton> buttonList;
    private Clip musicClip;

    public MapComponent(Player player1, Player player2, WarfieldPage page) {
        this.scoreboard = Game.scoreboard;
        this.player1 = player1;
        this.player2 = player2;
        this.map = player2.getPlayerMap();
        this.buttonMap = new HashMap<>();
        this.warfieldPage = page;
        this.buttonList = new ArrayList<>();
        initializePanel(true);
    }

    private void initializePanel(boolean isInit) {
        this.panel = new JPanel(new GridLayout(10, 10));
        this.panel.removeAll();

        if (isInit){
            buttonMap.clear();
            buttonList.clear();
        }

        for (List<Tile> row : map.getMap()) {
            for (Tile tile : row) {
                JButton button = new JButton();
                Color tileColor = getTileColor(tile);

                if(tile.isHit()){
                    disableButton(button);
                } else {
                    button.addActionListener(e -> handleTileClick(tile, button));
                }

                button.setBackground(tileColor);
                button.setOpaque(true);

                buttonMap.put(tile, button);
                buttonList.add(button);
                this.panel.add(button);
            }
        }

        this.panel.revalidate();
        this.panel.repaint();
    }

    private void disableButtons(Tile tile, JButton exceptionButton)
    {
        buttonList.remove(exceptionButton);
        for (JButton button : buttonList) {
            button.setFocusable(false);
            if(!button.isBackgroundSet()){
                button.setEnabled(false);
            }
        }
    }

    private void enableButtons(Tile tile, JButton exceptionButton) {
        buttonList.add(exceptionButton);
        for (JButton button : buttonList) {
            button.setFocusable(true);
            button.setEnabled(true);
        }
    }

    private void handleTileClick(Tile tile, JButton button) {

        int x = tile.getX();
        int y = tile.getY();
        String shipName = tile.getShipName(tile.getId_ship());

        if(! button.isFocusable())
        {
            return;
        }
        tile.setHit(true);
        disableButtons(tile, button);

        if (tile.isShip()) {
            scoreboard.hit(player1, x, y);
            this.player1.incrementScore();
            button.setBackground(Color.ORANGE);
            ImageIcon gifIcon = new ImageIcon("gui/src/assets/boom.gif");
            playSound("gui/src/assets/boom.wav");
            button.setIcon(gifIcon);
            if (tile.isDestroyed()) {
                scoreboard.destroy(player1, x, y, shipName);
                button.setBackground(Color.RED);
            }
        } else {
            ImageIcon gifIcon = new ImageIcon("gui/src/assets/glouglou.gif");
            playSound("gui/src/assets/ploof.wav");
            button.setIcon(gifIcon);
            scoreboard.miss(player1, x, y);
            button.setBackground(Color.BLUE);
        }

        disableButton(button);
        Timer timer = getTimer(tile, button);
        timer.start();
    }

    private Timer getTimer(Tile tile, JButton button) {
        Timer timer = new Timer(1300, e -> {
            if(this.player1.isWin()){
                System.out.println("\nEnd Game !");
                System.out.println(this.player1.getName() + " Wins");
                this.winPage = new WinPage(this.player1);
                this.winPage.display();
                this.warfieldPage.hide();
            }
            Game.turn += 1;
            System.out.println("score  " + this.player1.getName() + ": " + this.player1.getScore());
            System.out.println("end of turn: " + Game.turn + "\n");
            this.warfieldPage.revalidateComponents();
            enableButtons(tile, button);
        });

        timer.setRepeats(false); // Ensure the timer only runs once
        return timer;
    }

    public void updatePlayers(Player currentPlayer, Player enemyPlayer) {
        this.player1 = currentPlayer;
        this.player2 = enemyPlayer;
        this.map = enemyPlayer.getPlayerMap();
        initializePanel(true);
        display();
    }

    public void clearConsole() {
        // ANSI escape code to clear the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void display() {
        panel.revalidate();
        panel.repaint();
    }

    private void playSound(String filePath) {
        File musicFile = new File(filePath);
        if (!musicFile.exists()) {
            System.err.println("Music file not found at: " + musicFile.getAbsolutePath());
            return;
        }
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // ----- S E T T E R S -----
    public void disableButton(JButton button){
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setFocusable(false);
    }

    // ----- G E T T E R S -----

    public Tile getTile(int x, int y) {
        // Retrieve the tile at the specified coordinates
        if (x >= 0 && x < 10 && y >= 0 && y < 10) {
            return map.getMap().get(x).get(y);
        } else {
            throw new IndexOutOfBoundsException("Invalid coordinates: (" + x + ", " + y + ")");
        }
    }

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