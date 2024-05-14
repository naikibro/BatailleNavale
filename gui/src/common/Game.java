package common;

import map.Map;

public class Game {

    static public boolean isPlaying = false;
    static public int phase, turn;
    static public Map map;

    public Game(Map map){

        Game.map = map;
        Game.isPlaying = false;
        Game.phase = 0;
        Game.turn = 0;
    }

    // ----- M E T H O D S -----
    public boolean isWin(Player player1, Map mapPlayer2)
    {

        // TODO: check that the sum of the mapPlayer2 hit tiles is equal to
        //  the sum of the lengths of all the ennemies ships

        return isPlaying;
    }
}
