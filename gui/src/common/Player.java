package common;

import fleet.Fleet;
import map.Map;

public class Player {

    private int id;
    private Map realMap;
    private Map forecastMap;
    public String name;
    Fleet fleet;

    // ----- C O N S T R U C T O R -----

    public Player()
    {
        this.id = 0;
        this.name = "Computer";
        this.realMap = new Map();
        this.forecastMap = new Map();
        fleet = new Fleet();
    }

    public Player(String name)
    {
        this.id = 1;
        this.name = name;
        realMap = new Map();
        forecastMap = new Map();
        fleet = new Fleet();
    }

    // ----- G E T T E R S - S E T T E R S -----
    public Map getRealMap() {
        return realMap;
    }

    public void setRealMap(Map realMap) {
        this.realMap = realMap;
    }

    public Map getForecastMap() {
        return forecastMap;
    }

    public void setForecastMap(Map forecastMap) {
        this.forecastMap = forecastMap;
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

}
