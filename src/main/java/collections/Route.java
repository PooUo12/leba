package collections;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;



/**
 * The Route class represents a route
 */
public class Route implements Comparable<Route>{
    private int id;
    private String name;
    private Coordinates coordinates;
    private LocalDate creationDate;
    private LocationFrom locationFrom;
    private LocationTo locationTo;
    private float distance;



    public Route(float v){

    }

    public Route(int id, String name, Coordinates coordinates, LocalDate creationDate, LocationFrom locationFrom, LocationTo locationTo, Float distance) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.locationFrom = locationFrom;
        this.locationTo = locationTo;
        this.distance = distance;
    }


    /**
     * Returns the id of the object
     *
     * @return The id of the route.
     */
    public int getId() {
        return id;
    }


    /**
     * It sets the id of the object to the value passed in.
     *
     * @param id The id of the route.
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Returns the name of the route
     *
     * @return The name of the route.
     */
    @XmlElement
    public String getName() {
        return name;
    }


    /**
     * Returns the coordinates of the point
     *
     * @return The coordinates of the point.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }


    /**
     * Get the creation date of the object
     *
     * @return The creation date of the route.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }


    /**
     * The getLocationFrom() function returns the location from of the route
     *
     * @return The location from of the route.
     */
    public LocationFrom getLocationFrom() {
        return locationFrom;
    }

    /**
     * The getLocationTo() function returns the location to of the route
     *
     * @return The location to of the route.
     */
    public LocationTo getLocationTo() {
        return locationTo;
    }


    /**
     * Returns the distance of the route
     *
     * @return The distance of the route.
     */
    public float getDistance() {
        return distance;
    }





    /**
     * This method is used to compare two objects
     *
     * @param o The object to compare to.
     * @return The id of the route.
     */
    @Override
    public int compareTo(Route o) {
        return this.getId() - o.getId();
    }



    /**
     * Prints the organization's information
     *
     * @return The toString() method returns a string representation of the Organization object.
     */
    @Override
    public String toString() {
        String result = String.format("Id: %d\nName: %s\nCoordinates: {x: %f, y: %d}\nCreation Time: %s\nLocationFrom:{name: %s, x: %f, y: %f}\nLocationTo:{name: %s, x: %d, y: %d}\n",
                getId(), getName(), getCoordinates().getX(), getCoordinates().getY(), getCreationDate(), getLocationFrom().getName(), getLocationFrom().getx(),
                getLocationFrom().gety(),  getLocationTo().getName(), getLocationTo().get_x(), getLocationTo().get_y());
        if(getDistance() == 0) result += "Distance: null";
        else result += String.format("Distance: %s", getDistance());
        return result;

    }
}