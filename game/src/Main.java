import map.Map;
import pages.LandingPage;
import pages.WarfieldPage;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // ----- GLOBAL VARIABLES -----
        Map map = new Map();
        map.display();

        // ----- PAGE RENDERING -----
        SwingUtilities.invokeLater(() -> {
            WarfieldPage warfieldPage = new WarfieldPage();
            LandingPage landingPage = new LandingPage();

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
