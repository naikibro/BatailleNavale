import common.Scoreboard;
import map.Map;
import pages.LandingPage;
import pages.WarfieldPage;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // ----- GLOBAL VARIABLES -----
        Map map = new Map();
        Scoreboard scoreboard = new Scoreboard();

        // ----- PAGE RENDERING -----
        SwingUtilities.invokeLater(() -> {

            WarfieldPage warfieldPage = new WarfieldPage(scoreboard);
            LandingPage landingPage = new LandingPage();

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
