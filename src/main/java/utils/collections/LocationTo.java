package utils.collections;

import java.io.Serializable;

public class LocationTo implements Serializable {
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final int x;
    private final long y;

    public LocationTo(String name, int x, long y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * It returns the Location name.
     *
     * @return The name variable.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the x coordinate of the location from
     *
     * @return The value of the instance variable x.
     */
    public int get_x() {
        return x;
    }

    /**
     * Get the y coordinate of the location from
     *
     * @return The value of the instance variable y.
     */
    public long get_y() {
        return y;
    }
}