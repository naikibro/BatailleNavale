package common;

import fleet.Fleet;
import map.Map;
import map.Tile;
import ship.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {

    private int id;
    private int score = 0;
    private Map playerMap;
    public String name, imagePath;
    Fleet fleet;

    // ----- C O N S T R U C T O R -----

    public Player()
    {
        this.id = 1;
        this.name = "Computer";
        this.playerMap = new Map();
        this.imagePath = "gui/src/pages/player2.png";
        fleet = new Fleet();
    }

    public Player(String name)
    {
        this.id = 0;
        this.name = name;
        playerMap = new Map();
        this.imagePath = "gui/src/pages/player1.png";
        fleet = new Fleet();
    }

    // ----- G E T T E R S - S E T T E R S -----
    public int getScore() {
        return this.score;
    }

    public void incrementScore() {
        this.score += 1;
    }

    public boolean isWin() {
        return this.score == 21;
    }

    public Map getPlayerMap() {
        return this.playerMap;
    }

    public void setPlayerMap(Map playerMap) {
        this.playerMap = playerMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public void setFleet(Fleet fleet) {
        this.fleet = fleet;
    }

    public void randomlyPlaceShips(){
        // Variables
        List<Ship> ships = this.fleet.getShips();
        List<List<Tile>> map = this.playerMap.getMap();
        String[] array;
        List<String> directions;
        int taille_ship, id_ship, x, y, id_dir;
        Random random = new Random();
        boolean canPlaceShip = false;

        // Start
        for(Ship ship : ships ){
            System.out.println(ship.getName());
            taille_ship = ship.getLength();
            id_ship = ship.getId();
            canPlaceShip = false;
            while(!canPlaceShip){
                x = random.nextInt(10); // Génère une position aléatoire x dans le tableau
                y = random.nextInt(10); // Génère une position aléatoire y dans le tableau

                array = new String[]{"avant", "arriere", "haut", "bas"};
                directions = new ArrayList<>(Arrays.asList(array));
                while(!directions.isEmpty() && !canPlaceShip){
                    id_dir = random.nextInt(directions.size());
                    String direction = directions.remove(id_dir); // Choisit une direction aléatoire dans la liste
                    canPlaceShip = true;
                    // Initialise une variable pour vérifier si le navire peut être placé
                    switch (direction) {
                        case "avant":
                            for (int i = 0; i < taille_ship; i++) {
                                if (y + i >= 10 || map.get(x).get(y + i).getId_ship() != -1) { // Vérifie si les cases correspondantes sont libres
                                    canPlaceShip = false;
                                    break;
                                }
                            }
                            if (canPlaceShip) {
                                for (int i = 0; i < taille_ship; i++) {
                                    map.get(x).get(y + i).setId_ship(id_ship);// Place le navire dans le tableau
                                }
                            }
                            break;
                        case "arriere":
                            for (int i = 0; i < taille_ship; i++) {
                                if (y - i < 0 || map.get(x).get(y - i).getId_ship() != -1) { // Vérifie si les cases correspondantes sont libres
                                    canPlaceShip = false;
                                    break;
                                }
                            }
                            if (canPlaceShip) {
                                for (int i = 0; i < taille_ship; i++) {
                                    map.get(x).get(y - i).setId_ship(id_ship); // Place le navire dans le tableau
                                }
                            }
                            break;
                        case "haut":
                            for (int i = 0; i < taille_ship; i++) {
                                if (x - i < 0 || map.get(x - i).get(y).getId_ship() != -1) { // Vérifie si les cases correspondantes sont libres
                                    canPlaceShip = false;
                                    break;
                                }
                            }
                            if (canPlaceShip) {
                                for (int i = 0; i < taille_ship; i++) {
                                    map.get(x - i).get(y).setId_ship(id_ship); // Place le navire dans le tableau
                                }
                            }
                            break;
                        case "bas":
                            for (int i = 0; i < taille_ship; i++) {
                                if (x + i >= 10 || map.get(x + i).get(y).getId_ship() != -1) { // Vérifie si les cases correspondantes sont libres
                                    canPlaceShip = false;
                                    break;
                                }
                            }
                            if (canPlaceShip) {
                                for (int i = 0; i < taille_ship; i++) {
                                    map.get(x + i).get(y).setId_ship(id_ship); // Place le navire dans le tableau
                                }
                            }
                            break;
                    }

                    if (canPlaceShip) {
                        System.out.println("Le navire a été placé avec succès à la position : " + x + "," + y + " et à la direction : " + direction);
                    } else {
                        System.out.println("Impossible de placer le navire à la position : " + x + "," + y + " et à la direction : " + direction);
                        if(directions.isEmpty()){
                            System.out.println("Changement de position x et y...");
                        }
                    }
                }
            }
        }
        //End
        this.playerMap.setMap(map);
    }
    public void declineIdentity()
    {
        int tempId = this.id + 1;
        System.out.println("\nPLAYER " + tempId + " : " + getName() + "\n");
        getFleet().printFleetStatus();
        System.out.println("\n ---------------- \n");
    }

}
