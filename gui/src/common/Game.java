package common;

import map.Map;

public class Game {

    static public boolean isPlaying = false;
    static public int phase, turn;
    static public Map map1, map2;
    static public Scoreboard scoreboard;
    static public Player player1;
    static public Player player2;

    public Game(){

        Game.map1 = new Map();
        Game.map2 = new Map();
        Game.isPlaying = false;
        Game.phase = 0;
        Game.turn = 0;
        Game.scoreboard = new Scoreboard();
        Game.player1 = new Player("");
        Game.player2 = new Player();
    }

    // ----- M E T H O D S -----
    public boolean isWin(Player player1, Map mapPlayer2)
    {

        // TODO: check that the sum of the mapPlayer2 hit tiles is equal to
        //  the sum of the lengths of all the ennemies ships

        return isPlaying;
    }
}
