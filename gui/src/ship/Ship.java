package ship;

import java.io.Serializable;

public class Ship implements Serializable {
    private int id;
    protected String name;
    private boolean destroy;
    protected int length;
    private int touched;

    public Ship(int id) {
        this.id = id;
        this.destroy = false;
        this.touched = 0;
    }

    public void updateStats()
    {
        if(touched == length)
        {
            setDestroy(true);
        }
    }

    public boolean isDestroy() {
        return this.destroy;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    public int getLength() {
        return this.length;
    }

    public int getTouched() {
        return this.touched;
    }

    public void incrementTouched() {
        this.touched++;
    }
}
