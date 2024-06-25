package map;

import common.Game;
import common.Player;

public class Tile {
    private int x, y;
    private int id_ship;
    private boolean isHit;

    // ----- C O N S T R U C T O R S -----
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.id_ship = -1;
        this.isHit = false;
    }

    // ----- M E T H O D S -----
    public boolean isShip() {
        return id_ship != -1;
    }

    public boolean isDestroyed() {
        // This is a placeholder for actual logic to determine if the ship is destroyed.
        // Actual implementation will depend on the game's logic for determining ship destruction.
        return isHit && isShip();  // Example condition for simplicity
    }

    // ----- G E T T E R S - S E T T E R S -----
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public int getId_ship() {
        return id_ship;
    }

    public String getShipName(int id_ship){

        Player tempPlayer = (Game.turn % 2 == 0) ? Game.player1 : Game.player2;
        return tempPlayer.getShipNameById(id_ship);
    }

    public void setId_ship(int id_ship) {
        this.id_ship = id_ship;
    }
}
