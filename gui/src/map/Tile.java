package map;

public class Tile {

    private int id, x, y;
    private boolean isHit;

    // ----- C O N S T R U C T O R S -----

    Tile(int x, int y) {

        this.x = x;
        this.y = y;
        this.isHit = false;
    }

    Tile(int id, int x, int y, boolean isHit) {
        this.x = x;
        this.y = y;
        this.isHit = isHit;
    }

    // ----- M E T H O D S -----


    // ----- S E T T E R S -----

}
