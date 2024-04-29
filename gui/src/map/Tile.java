package map;

public class Tile {

    private int x, y;
    private int id_ship;

    private boolean isHit;

    // ----- C O N S T R U C T O R S -----

    Tile(int x, int y) {

        this.x = x;
        this.y = y;
        this.isHit = false;
    }

    Tile(int x, int y, boolean isHit) {
        this.x = x;
        this.y = y;
        this.isHit = isHit;
    }

    // ----- M E T H O D S -----


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

}
