package collections;

public class Coordinates {
    private final double x;
    private final Long y;


    public Coordinates(double x, Long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x coordinate of the point
     *
     * @return The value of the instance variable x.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the point
     *
     * @return The y value of the point.
     */
    public Long getY() {
        return y;
    }


    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}