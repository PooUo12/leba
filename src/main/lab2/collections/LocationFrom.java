package collections;

public class LocationFrom {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private final float x;
    private final float y;

    public LocationFrom(String name, float y, float x) {
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
    public float getx() {
        return x;
    }

    /**
     * Get the y coordinate of the location from
     *
     * @return The value of the instance variable y.
     */
    public float gety() {
        return y;
    }
}