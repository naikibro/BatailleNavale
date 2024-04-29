package map;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<List<Tile>> map;

    public Map()
    {
        map = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Tile> sublist = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                sublist.add(new Tile(i, j));
            }
            map.add(sublist);
        }
    }

    public void display() {
        for (List<Tile> row : map) {
            for (Object element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public List<List<Tile>> getMap() {
        return map;
    }

}
