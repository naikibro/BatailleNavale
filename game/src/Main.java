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
        System.out.println("----- F L E E T - I N I T -----\n");
        if (tempFile.exists()) {
            player1.setFleet(deserializeFleet("temp.ser"));
        } else {
            player1.setFleet(deserializeFleet("fleet.ser"));
            player2.setFleet(deserializeFleet("fleet.ser"));
        }
        player1.randomlyPlaceShips();
        player2.randomlyPlaceShips();
        System.out.println("\nAll ships have been placed successfully");

        // ----- PAGE RENDERING -----
        SwingUtilities.invokeLater(() -> {
            WarfieldPage warfieldPage = new WarfieldPage(scoreboard, player1, player2);
            LandingPage landingPage = new LandingPage(player1, player2);
            GameOverPage gameOverPage = new GameOverPage(scoreboard, player1, player2);

            // Set up the action for the play button
            landingPage.setPlayAction(e -> {
                System.out.println("Let's play !\n");
                warfieldPage.display();
                landingPage.hide();
            });

            warfieldPage.setEndGameAction(e -> {
                System.out.println("End Game !");
                gameOverPage.display();
                warfieldPage.hide();
            });

            System.out.println("\n----- G A M E - L O O P -----\n");
            landingPage.display();
        });

        // ----- MAIN GAME LOOP -----

    }
}
