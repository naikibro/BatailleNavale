package fleet;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jdk.jfr.Description;
import ship.*;

public class Fleet implements Serializable {

    @Serial
    private static final long serialVersionUID = 6399875888803746427L;
    private List<Ship> ships;

    // ----- C O N S T R U C T O R S -----
    public Fleet() {
        this.ships = new ArrayList<>();
    }

    public static Fleet createDefaultPlayerFleet(int playerId) {
        Fleet fleet = new Fleet();

        // Add default ships to the fleet
        if (playerId == 1) {
            fleet.addShip(new Submarine(1));
            fleet.addShip(new Destroyer(2));
            fleet.addShip(new Cruiser(3));
            fleet.addShip(new BattleShip(4));
            fleet.addShip(new Carrier(5));
        } else if (playerId == 2) {
            fleet.addShip(new Submarine(1));
            fleet.addShip(new Destroyer(2));
            fleet.addShip(new BattleShip(3));
            fleet.addShip(new BattleShip(4));
            fleet.addShip(new Carrier(5));
        }

        return fleet;
    }

    // ----- G E T T E R S - S E T T E R S -----
    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public void removeShip(Ship ship) {
        ships.remove(ship);
    }

    public List<Ship> getShips() {
        return ships;
    }

    // ----- M E T H O D S -----

    public void printFleetStatus() {
        System.out.println("Fleet Status:");
        for (Ship ship : ships) {
            System.out.println("Ship ID: " + ship.getId() + ", Name: " + ship.getName() + ", Destroyed: " + ship.isDestroy());
        }
    }

    public void updateFleetStats()
    {
        for (Ship ship : ships) {
            ship.updateStats();
        }
    }

    // ----- S E R I A L I Z A T I O N -----

    @Description(
            "This will be used as a way to save a current party on close of windows or with dedicated UX"
    )
    public static void serializeFleet(Fleet fleet) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("fleet.ser"))) {
            out.writeObject(fleet);
            System.out.println("Fleet serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Description(
            "This will be used as way to load a previous game save if existing and a default state if not"
    )
    public static Fleet deserializeFleet(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Fleet) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
