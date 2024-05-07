import common.Player;
import common.Scoreboard;
import fleet.Fleet;
import map.Map;
import pages.LandingPage;
import pages.WarfieldPage;
import ship.Cruiser;
import ship.Destroyer;
import ship.Submarine;
import common.Player;

import javax.swing.*;

import java.io.File;

import static fleet.Fleet.deserializeFleet;

public class Main {
    public static void main(String[] args) {

        // ----- GLOBAL VARIABLES -----
        Map map = new Map();
        Scoreboard scoreboard = new Scoreboard();
        Player player1 = new Player("");
        Player player2 = new Player("Computer");

        // Creating fleets for player1 and player2 with default ships
        Fleet player1Fleet = new Fleet();
        Fleet player2Fleet = new Fleet();

        File tempFile = new File("temp.ser");
        if (tempFile.exists()) {
            System.out.println("Loading fleet from temp file");
            Fleet tempFleet = deserializeFleet("temp.ser");
            if (tempFleet != null) {
                player1Fleet = tempFleet;
            }
        } else {
            System.out.println("Loading fleet from default config file");
            player1Fleet = deserializeFleet("fleet.ser");
            player2Fleet = deserializeFleet("fleet.ser");
        }

        // Print fleet status for player1 (optional)
        player1Fleet.printFleetStatus();
        player2Fleet.printFleetStatus();

        // ----- PAGE RENDERING -----
        SwingUtilities.invokeLater(() -> {

            WarfieldPage warfieldPage = new WarfieldPage(scoreboard, player1, player2);
            LandingPage landingPage = new LandingPage(player1);

            // TODO: Add any new page here

            System.out.println("Instantiate");
            landingPage.setPlayAction(e -> {
                System.out.println("Let's play !");
                warfieldPage.display();
                landingPage.hide();
            });

            System.out.println("Instantiated");
            landingPage.display();
        });

        // ----- MAIN GAME LOOP -----

    }
}
