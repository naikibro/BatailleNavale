package map;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<List<Object>> map;

    public Map()
    {
        map = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Object> sublist = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                sublist.add(0);
            }
            map.add(sublist);
        }
    }

    public void display() {
        for (List<Object> row : map) {
            for (Object element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public List<List<Object>> getMap() {
        return map;
    }

}
