package common;

import map.Map;

import javax.swing.*;
import java.util.List;

public class Game {

    public boolean isPlaying = false;
    public int phase;
    public Map map;

    Game(Map map){

        this.map = map;
        this.isPlaying = false;
        this.phase = 0;
    }

    // ----- M E T H O D S -----
    public boolean isWin(Player player1, Map mapPlayer2)
    {

        // TODO: check that the sum of the mapPlayer2 hit tiles is equal to
        //  the sum of the lengths of all the ennemies ships

        return isPlaying;
    }
}
