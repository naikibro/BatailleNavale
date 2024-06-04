import common.Game;
import common.Player;
import common.Scoreboard;
import map.Map;
import pages.GameOverPage;
import pages.LandingPage;
import pages.WarfieldPage;

import javax.swing.*;
import java.io.File;

import static fleet.Fleet.deserializeFleet;

public class Main {
    public static void main(String[] args) {

        // ----- GLOBAL VARIABLES -----
        Game game = new Game();

        Player player1 = Game.player1;
        Player player2 = Game.player2;
        Scoreboard scoreboard = Game.scoreboard;

        // Loading the fleets from Serialization if exists
        File tempFile = new File("temp.ser");
        if (tempFile.exists()) {
            System.out.println("Loading fleet from save file");
            player1.setFleet(deserializeFleet("temp.ser"));
        } else {
            System.out.println("Loading fleet from default config file");
            player1.setFleet(deserializeFleet("fleet.ser"));
            player2.setFleet(deserializeFleet("fleet.ser"));
        }
        player1.randomlyPlaceShips();
        player2.randomlyPlaceShips();

        // ----- PAGE RENDERING -----
        SwingUtilities.invokeLater(() -> {
            WarfieldPage warfieldPage = new WarfieldPage(scoreboard, player1, player2);
            LandingPage landingPage = new LandingPage(player1, player2);
            GameOverPage gameOverPage = new GameOverPage(scoreboard, player1, player2);

            // Set up the action for the play button
            landingPage.setPlayAction(e -> {
                System.out.println("Let's play !");
                warfieldPage.display();
                warfieldPage.revalidateComponents(false);
                landingPage.hide();
            });

            warfieldPage.setEndGameAction(e -> {
                System.out.println("End Game !");
                gameOverPage.display();
                warfieldPage.hide();
            });

            System.out.println("Instantiated");
            landingPage.display();
        });

        // ----- MAIN GAME LOOP -----

    }
}
